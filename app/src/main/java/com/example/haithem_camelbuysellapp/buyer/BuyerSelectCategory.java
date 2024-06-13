package com.example.haithem_camelbuysellapp.buyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.haithem_camelbuysellapp.R;

public class BuyerSelectCategory extends AppCompatActivity {
    ImageButton camel, meat, beauty;
    TextView txtCamel, txtMeat, txtBeauty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_select_category);

        camel= findViewById(R.id.camel_b);
        meat= findViewById(R.id.meat_b);
        beauty= findViewById(R.id.beauty_b);
        txtCamel= findViewById(R.id.txtCamel_b);
        txtMeat= findViewById(R.id.txtMeat_b);
        txtBeauty= findViewById(R.id.txtBeauty_b);

        camel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerSelectCategory.this, BuyCamel.class));
                finish();
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerSelectCategory.this, BuyMeat.class));
                finish();
            }
        });

        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerSelectCategory.this, BuyBeauty.class));
                finish();
            }
        });

        txtCamel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerSelectCategory.this, BuyCamel.class));
                finish();
            }
        });

        txtMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerSelectCategory.this, BuyMeat.class));
                finish();
            }
        });

        txtBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerSelectCategory.this, BuyBeauty.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BuyerSelectCategory.this, BuyerHome.class));
        finish();
    }
}