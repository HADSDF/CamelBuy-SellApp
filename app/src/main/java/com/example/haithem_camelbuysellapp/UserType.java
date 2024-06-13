package com.example.haithem_camelbuysellapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haithem_camelbuysellapp.buyer.BuyerHome;
import com.example.haithem_camelbuysellapp.buyer.BuyerLogin;
import com.example.haithem_camelbuysellapp.seller.SellerHome;
import com.example.haithem_camelbuysellapp.seller.SellerLogin;

public class UserType extends AppCompatActivity {
    Button buyer, seller;
    public static final String SHARED_PREFS= "sharedPrefs";
    String ff1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        buyer= (Button) findViewById(R.id.buyer);
        seller= (Button) findViewById(R.id.seller);

        checkBox();

        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserType.this, BuyerLogin.class));
                finish();
            }
        });

        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserType.this, SellerLogin.class));
                finish();
            }
        });

    }

    private void checkBox() {
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check= sharedPreferences.getString("name", "");

        if (check.equals("buyer")){
            startActivity(new Intent(UserType.this, BuyerHome.class));
            finish();
        }
        if (check.equals("seller")){
            startActivity(new Intent(UserType.this, SellerHome.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}