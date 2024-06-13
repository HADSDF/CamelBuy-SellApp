package com.example.haithem_camelbuysellapp.buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserCamel;
import com.example.haithem_camelbuysellapp.UserCart;
import com.example.haithem_camelbuysellapp.UserInformation;
import com.example.haithem_camelbuysellapp.seller.ImageAdapterUpdateCamel;
import com.example.haithem_camelbuysellapp.seller.SellerSelectCategory1;
import com.example.haithem_camelbuysellapp.seller.UpdateCamel;
import com.example.haithem_camelbuysellapp.seller.UpdateCamelOpen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class BuyCamel extends AppCompatActivity implements ImageAdapterBuyCamel.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapterBuyCamel mAdapter;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<UserCamel> mUploads, mUploads1;
    private ProgressBar mProgressCircle;
    private FirebaseDatabase firebaseDatabase;
    String s_name, s_phone;

    String im_name, im_price, im_size, im_detail, im_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_camel);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view7);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle7);

        mUploads = new ArrayList<>();
        mUploads1 = new ArrayList<>();
        mAdapter = new ImageAdapterBuyCamel(BuyCamel.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(BuyCamel.this);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Items");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot ds : postSnapshot.child("camel").getChildren()) {
                        UserCamel userCamel = ds.getValue(UserCamel.class);
                        userCamel.setKey(ds.getKey());
                        mUploads.add(userCamel);
                        UserCamel userCamel1= postSnapshot.getValue(UserCamel.class);
                        userCamel1.setKey(postSnapshot.getKey());
                        mUploads1.add(userCamel1);
                    }

                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BuyCamel.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        UserCamel selectUId= mUploads1.get(position);
        final String selectedUID= selectUId.getKey();

        UserCamel selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Items").child(selectedUID).child("camel").child(selectedKey);
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserCamel userUpload= dataSnapshot.getValue(UserCamel.class);
                im_name = userUpload.getItemName();
                im_price = userUpload.getItemPrice();
                im_detail= userUpload.getItemSpecification();
                im_pic = userUpload.getItemUrl();
                s_name= userUpload.getItemSName();
                s_phone=userUpload.getItemSPhone();


                DatabaseReference ref3= FirebaseDatabase.getInstance().getReference("Cart").child(user.getUid()).child("my");
                UserCart userCart = new UserCart(im_name, im_price, im_detail, im_pic, selectedKey, selectedUID, s_name, s_phone);
                ref3.child(ref3.push().getKey()).setValue(userCart);
                startActivity(new Intent(BuyCamel.this, BuyerHome.class));
                Toast.makeText(BuyCamel.this, "Item added to Cart Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BuyCamel.this, BuyerSelectCategory.class));
        finish();
    }
}