package com.example.haithem_camelbuysellapp.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.haithem_camelbuysellapp.R;

public class SellerSelectCategory extends AppCompatActivity {
    ImageButton camel, meat, beauty;
    TextView txtCamel, txtMeat, txtBeauty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_select_category);

        camel= findViewById(R.id.camel);
        meat= findViewById(R.id.meat);
        beauty= findViewById(R.id.beauty);
        txtCamel= findViewById(R.id.txtCamel);
        txtMeat= findViewById(R.id.txtMeat);
        txtBeauty= findViewById(R.id.txtBeauty);

        camel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory.this, AddCamel.class));
                finish();
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory.this, AddMeat.class));
                finish();
            }
        });

        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory.this, AddBeauty.class));
                finish();
            }
        });

        txtCamel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory.this, AddCamel.class));
                finish();
            }
        });

        txtMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory.this, AddMeat.class));
                finish();
            }
        });

        txtBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerSelectCategory.this, AddBeauty.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SellerSelectCategory.this, SellerHome.class));
        finish();
    }
}