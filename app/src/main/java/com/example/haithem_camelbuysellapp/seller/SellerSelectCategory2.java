package com.example.haithem_camelbuysellapp.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.haithem_camelbuysellapp.R;

public class SellerSelectCategory2 extends AppCompatActivity {
    ImageButton camel, meat, beauty;
    TextView txtCamel, txtMeat, txtBeauty;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_select_category2);

        camel= findViewById(R.id.camel2);
        meat= findViewById(R.id.meat2);
        beauty= findViewById(R.id.beauty2);
        txtCamel= findViewById(R.id.txtCamel2);
        txtMeat= findViewById(R.id.txtMeat2);
        txtBeauty= findViewById(R.id.txtBeauty2);

        camel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory2.this, DeleteCamel.class));
                finish();
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory2.this, DeleteMeat.class));
                finish();
            }
        });

        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory2.this, DeleteBeauty.class));
                finish();
            }
        });

        txtCamel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory2.this, DeleteCamel.class));
                finish();
            }
        });

        txtMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory2.this, DeleteMeat.class));
                finish();
            }
        });

        txtBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory2.this, DeleteBeauty.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SellerSelectCategory2.this, SellerHome.class));
        finish();
    }
}