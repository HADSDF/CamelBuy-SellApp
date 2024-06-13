package com.example.haithem_camelbuysellapp.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.haithem_camelbuysellapp.R;

public class SellerSelectCategory1 extends AppCompatActivity {
    ImageButton camel, meat, beauty;
    TextView txtCamel, txtMeat, txtBeauty;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_select_category1);

        camel= findViewById(R.id.camel1);
        meat= findViewById(R.id.meat1);
        beauty= findViewById(R.id.beauty1);
        txtCamel= findViewById(R.id.txtCamel1);
        txtMeat= findViewById(R.id.txtMeat1);
        txtBeauty= findViewById(R.id.txtBeauty1);

        camel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory1.this, UpdateCamel.class));
                finish();
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory1.this, UpdateMeat.class));
                finish();
            }
        });

        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory1.this, UpdateBeauty.class));
                finish();
            }
        });

        txtCamel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory1.this, UpdateCamel.class));
                finish();
            }
        });

        txtMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory1.this, UpdateMeat.class));
                finish();
            }
        });

        txtBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory1.this, UpdateBeauty.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SellerSelectCategory1.this, SellerHome.class));
        finish();
    }
}