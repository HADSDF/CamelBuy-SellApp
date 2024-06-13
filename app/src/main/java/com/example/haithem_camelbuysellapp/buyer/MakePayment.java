package com.example.haithem_camelbuysellapp.buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserCart;
import com.example.haithem_camelbuysellapp.UserCheckStatistics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MakePayment extends AppCompatActivity {

    TextView bo_name, bo_price, bo_detail, so_name,so_phone;
    EditText credit, cus_address, buy_quantity;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    Button confirm;
    String user_credit, text, user_address, user_quantity;
    String getKey;
    String img_price, img_name, img_detail, img_url, img_sName, img_sPhone, img_UId;
    ImageView imgGet;

    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    DatabaseReference ref, ref1;
    String user_name, user_phone;
    String status= "Processing";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        confirm= (Button) findViewById(R.id.confirm);
        bo_name= (TextView) findViewById(R.id.bo_name);
        bo_price= (TextView) findViewById(R.id.bo_price);
        bo_detail= (TextView) findViewById(R.id.bo_detail);
        so_name= (TextView) findViewById(R.id.so_name);
        so_phone= (TextView) findViewById(R.id.so_phone);
        credit= (EditText) findViewById(R.id.credit);
        buy_quantity= (EditText) findViewById(R.id.buy_quantity);
        cus_address= (EditText) findViewById(R.id.cus_Address);
        imgGet= (ImageView) findViewById(R.id.imgGet);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (getIntent().hasExtra("key")) {
            getKey = getIntent().getStringExtra("key");
        }

        credit.setVisibility(View.INVISIBLE);

        mStrorage = FirebaseStorage.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("Cart").child(user.getUid()).child("my").child(getKey);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    UserCart userCart = snapshot.getValue(UserCart.class);
                    bo_name.setText(userCart.getCartName());
                    bo_price.setText(userCart.getCartPrice());
                    bo_detail.setText(userCart.getCartDetail());
                    so_name.setText(userCart.getItemSName());
                    so_phone.setText(userCart.getItemSPhone());
                    Picasso.get()
                            .load(userCart.getCartUrl())
                            .placeholder(R.mipmap.ic_launcher)
                            .fit()
                            .centerCrop()
                            .into(imgGet);

                    img_name= userCart.getCartName();
                    img_sName= userCart.getItemSName();
                    img_price= userCart.getCartPrice();
                    img_UId= userCart.getCartUid();


                }
                if (!snapshot.exists()){
                    Toast.makeText(MakePayment.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MakePayment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateQuantity()){
                    int buy_price=Integer.parseInt(img_price);
                    int int_quant= Integer.parseInt(user_quantity);

                    double final_price= buy_price * int_quant;
                    String display_price = Double.toString(final_price);
                    bo_price.setText(display_price);

                    //getTodatDate
                Date today = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String todayDate = sdf.format(today);


                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                text = radioButton.getText().toString();
                if (text.equals("Cash on delivery")) {
                    if (validate1()) {
                        ref.getRef().removeValue();
                        ref1= FirebaseDatabase.getInstance().getReference("Statistics").child(img_UId).child("all");
                        UserCheckStatistics userCheckStatistics= new UserCheckStatistics(img_name, img_sName, todayDate, user_quantity);
                        ref1.child(ref1.push().getKey()).setValue(userCheckStatistics);
                        Toast.makeText(MakePayment.this, "Order Confirmed Successfully", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MakePayment.this, "Thank you for choosing us", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MakePayment.this, BuyerHome.class));
                        finish();
                    }
                } else {
                    credit.setVisibility(View.VISIBLE);
                    if (validate1()) {
                        if (validate()) {
                            ref.getRef().removeValue();
                            ref1= FirebaseDatabase.getInstance().getReference("Statistics").child(img_UId).child("all");
                            UserCheckStatistics userCheckStatistics= new UserCheckStatistics(img_name, img_sName, todayDate, user_quantity);
                            ref1.child(ref1.push().getKey()).setValue(userCheckStatistics);
                            Toast.makeText(MakePayment.this, "Order & Payment done successfully", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MakePayment.this, "Thank you for choosing us", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MakePayment.this, BuyerHome.class));
                            finish();
                        }//validate end
                    }
                }
            }
            }
        });
    }

    private Boolean validate(){
        boolean result= false;

        user_credit = credit.getText().toString();

        if(user_credit.isEmpty()){
            Toast.makeText(this, "Enter credit card number", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    private Boolean validate1(){
        boolean result= false;

        user_address= cus_address.getText().toString();

        if(user_address.isEmpty()){
            Toast.makeText(this, "Enter address for delivery", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    private Boolean validateQuantity(){
        boolean result= false;

        user_quantity= buy_quantity.getText().toString();

        if(user_quantity.isEmpty()){
            Toast.makeText(this, "Enter Item Quantity", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MakePayment.this, Cart.class));
        finish();
    }
}