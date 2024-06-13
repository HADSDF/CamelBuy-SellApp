package com.example.haithem_camelbuysellapp.buyer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserInformation;
import com.example.haithem_camelbuysellapp.seller.SellerHome;
import com.example.haithem_camelbuysellapp.seller.SellerProfile;
import com.example.haithem_camelbuysellapp.seller.SellerUpdateProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BuyerUpdateProfile extends AppCompatActivity {
    private EditText u_fname, u_phone, u_add;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private Button update;
    String  user_fname, user_uphone, user_add;
    NetworkInfo nInfo;
    private FirebaseDatabase firebaseDatabase;
    String ff1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_update_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        update=(Button) findViewById(R.id.update1);
        u_fname= (EditText)findViewById(R.id.u_fname1);
        u_phone= (EditText)findViewById(R.id.u_phone1);
        u_add= (EditText)findViewById(R.id.u_add1);

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        //get firebase user
        user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Profile");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation userInformation= dataSnapshot.getValue(UserInformation.class);
                u_fname.setText(userInformation.getUserName());
                u_phone.setText(userInformation.getUserPhone());
                u_add.setText(userInformation.getUserAddress());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BuyerUpdateProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    if (validate()) {
                        if (nInfo != null && nInfo.isConnected()) {
                            UserInformation userInformation = new UserInformation(user_fname, user_uphone, user_add);
                            ref.setValue(userInformation);
                            Toast.makeText(BuyerUpdateProfile.this, "Profile is Updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(BuyerUpdateProfile.this, BuyerHome.class));
                            finish();

                        }
                    } else {
                        Toast.makeText(BuyerUpdateProfile.this, "Network is not available", Toast.LENGTH_LONG).show();
                    }
                }//user null
            }
        });
    }

    private Boolean validate(){
        boolean result= false;

        user_fname = u_fname.getText().toString();
        user_uphone=u_phone.getText().toString();
        user_add= u_add.getText().toString();
        if(user_fname.isEmpty() || user_uphone.isEmpty() || user_add.isEmpty()){
            Toast.makeText(this, "Fill every required information", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BuyerUpdateProfile.this, BuyerProfile.class));
        finish();
    }
}