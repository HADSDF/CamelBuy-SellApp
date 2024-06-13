package com.example.haithem_camelbuysellapp.seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserCamel;
import com.example.haithem_camelbuysellapp.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UpdateCamelOpen extends AppCompatActivity {
    private static final String TAG = "ImageOpen";
    EditText p_name, p_price, p_height, p_detail, p_weight;
    ImageView image;
    Button update_service;
    String u_s_name, u_s_price, u_s_height, u_s_detail, u_s_weight;
    String UID, key, getCat;
    private FirebaseAuth firebaseAuth;
    DatabaseReference ref;
    private FirebaseUser user;
    String imageUrl;
    String s_name, s_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_camel_open);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (getIntent().hasExtra("key")) {
            key= getIntent().getStringExtra("key");
        }

        p_name = findViewById(R.id.pic_name);
        p_price = findViewById(R.id.pic_Price);
        p_height = findViewById(R.id.pic_height);
        p_weight = findViewById(R.id.pic_weight);
        p_detail = findViewById(R.id.pic_detail);
        image = findViewById(R.id.pic);
        update_service= findViewById(R.id.update_item);

        final DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Profile");
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation userInformation= dataSnapshot.getValue(UserInformation.class);
                s_name= userInformation.getUserName();
                s_phone=userInformation.getUserPhone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateCamelOpen.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        ref= FirebaseDatabase.getInstance().getReference("Items").child(user.getUid()).child("camel").child(key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserCamel userCamel= snapshot.getValue(UserCamel.class);
                p_name.setText(userCamel.getItemName());
                p_price.setText(userCamel.getItemPrice());
                p_height.setText(userCamel.getItemHeight());
                p_weight.setText(userCamel.getItemWeight());
                p_detail.setText(userCamel.getItemSpecification());
                imageUrl= userCamel.getItemUrl();
                Picasso.get()
                        .load(userCamel.getItemUrl())
                        .into(image);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateCamelOpen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        update_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    UserCamel userCamel = new UserCamel(u_s_name, u_s_price, u_s_detail, u_s_height, u_s_weight, imageUrl, s_name, s_phone);
                    ref.setValue(userCamel);
                    startActivity(new Intent(UpdateCamelOpen.this, SellerHome.class));
                    Toast.makeText(UpdateCamelOpen.this, "Camel Details are Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }

    private Boolean validate(){
        boolean result= false;

        u_s_name = p_name.getText().toString();
        u_s_price = p_price.getText().toString();
        u_s_height= p_height.getText().toString();
        u_s_weight= p_weight.getText().toString();
        u_s_detail = p_detail.getText().toString();
        if(u_s_name.isEmpty() || u_s_price.isEmpty() || u_s_height.isEmpty() || u_s_weight.isEmpty() || u_s_detail.isEmpty()){
            Toast.makeText(this, "Fill every required information", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateCamelOpen.this,UpdateCamel.class));
        finish();
    }
}