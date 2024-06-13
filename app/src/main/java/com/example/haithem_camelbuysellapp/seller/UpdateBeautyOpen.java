package com.example.haithem_camelbuysellapp.seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserBeauty;
import com.example.haithem_camelbuysellapp.UserInformation;
import com.example.haithem_camelbuysellapp.UserMeat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UpdateBeautyOpen extends AppCompatActivity {
    private static final String TAG = "ImageOpen";
    EditText p_name, p_price, p_detail, p_quality;
    ImageView image;
    Button update_service;
    String u_s_name, u_s_price, u_s_detail, u_s_quality;
    String UID, key, getCat;
    private FirebaseAuth firebaseAuth;
    DatabaseReference ref;
    private FirebaseUser user;
    String imageUrl;
    String s_name, s_phone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_beauty_open);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (getIntent().hasExtra("key")) {
            key= getIntent().getStringExtra("key");
        }

        p_name = findViewById(R.id.pic_name2);
        p_price = findViewById(R.id.pic_Price2);
        p_quality = findViewById(R.id.pic_quality2);
        p_detail = findViewById(R.id.pic_detail2);
        image = findViewById(R.id.pic2);
        update_service= findViewById(R.id.update_item2);

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
                Toast.makeText(UpdateBeautyOpen.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        ref= FirebaseDatabase.getInstance().getReference("Items").child(user.getUid()).child("beauty").child(key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserBeauty userBeauty = snapshot.getValue(UserBeauty.class);
                p_name.setText(userBeauty.getItemName());
                p_price.setText(userBeauty.getItemPrice());
                p_quality.setText(userBeauty.getItemQuality());
                p_detail.setText(userBeauty.getItemSpecification());
                imageUrl= userBeauty.getItemUrl();
                Picasso.get()
                        .load(userBeauty.getItemUrl())
                        .into(image);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateBeautyOpen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        update_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    UserBeauty userBeauty= new UserBeauty(u_s_name, u_s_price, u_s_detail, u_s_quality, imageUrl, s_name, s_phone);
                    ref.setValue(userBeauty);
                    startActivity(new Intent(UpdateBeautyOpen.this, SellerHome.class));
                    Toast.makeText(UpdateBeautyOpen.this, "Ornaments Details are Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }

    private Boolean validate(){
        boolean result= false;

        u_s_name = p_name.getText().toString();
        u_s_price = p_price.getText().toString();
        u_s_quality= p_quality.getText().toString();
        u_s_detail = p_detail.getText().toString();
        if(u_s_name.isEmpty() || u_s_price.isEmpty() || u_s_quality.isEmpty() || u_s_detail.isEmpty()){
            Toast.makeText(this, "Fill every required information", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateBeautyOpen.this,UpdateBeauty.class));
        finish();
    }
}