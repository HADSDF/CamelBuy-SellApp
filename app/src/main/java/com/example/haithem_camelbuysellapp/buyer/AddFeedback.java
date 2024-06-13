package com.example.haithem_camelbuysellapp.buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserBeauty;
import com.example.haithem_camelbuysellapp.UserFeedback;
import com.example.haithem_camelbuysellapp.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddFeedback extends AppCompatActivity {
    TextView rateCount, showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String temp;
    String rev, r_count, r_ser_name;
    Spinner spinner, spinnerSeller;
    String[] category= {"Select Category","Camel","Camel Meat", "Camel Beauty Ornaments"};
    String s, value, s1, value1;
    String final_sellerName;

    List<String> sellerNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        rateCount= (TextView)findViewById(R.id.rateCount);
        showRating= (TextView) findViewById(R.id.showRating);
        review= (EditText) findViewById(R.id.review);
        submit= (Button) findViewById(R.id.submitBtn);
        ratingBar= (RatingBar) findViewById(R.id.ratingBar);

        spinner= (Spinner) findViewById(R.id.spinner);
        spinnerSeller= (Spinner) findViewById(R.id.spinnerSeller);


        ArrayAdapter<String> adapter= new ArrayAdapter<String>(AddFeedback.this, android.R.layout.simple_spinner_item, category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sellerNames);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeller.setAdapter(adapter1);

        DatabaseReference refm = FirebaseDatabase.getInstance().getReference("Users");
        refm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot ds : postSnapshot.getChildren()) {
                        UserInformation userInformation= ds.getValue(UserInformation.class);
                        String seller_na= userInformation.getUserName();
                        sellerNames.add(seller_na);
                    }

                }
                adapter1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddFeedback.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value= adapterView.getItemAtPosition(i).toString();
                s= value;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerSeller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value1= adapterView.getItemAtPosition(i).toString();
                s1= value1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue= ratingBar.getRating();
                if (rateValue<=1 && rateValue>0)
                    rateCount.setText("Bad "+rateValue + "/5");
                else if (rateValue<=2 && rateValue>1)
                    rateCount.setText("OK "+rateValue + "/5");
                else if (rateValue<=3 && rateValue>2)
                    rateCount.setText("Good "+rateValue + "/5");
                else if (rateValue<=4 && rateValue>3)
                    rateCount.setText("Very Good "+rateValue + "/5");
                else if (rateValue<=5 && rateValue>4)
                    rateCount.setText("Best "+rateValue + "/5");

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()){
                    if (valid1()) {
                        if (validSellerName()){
                        temp = rateCount.getText().toString();
                        if (user != null) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Feedbacks");
                            UserFeedback userFeedback = new UserFeedback(r_ser_name, temp, rev, final_sellerName);
                            ref.child(ref.push().getKey()).setValue(userFeedback);
                            Toast.makeText(AddFeedback.this, "Feedback Submitted Successfully", Toast.LENGTH_SHORT).show();
                            showRating.setText("Your Rating: \n" + "Catagory: " + r_ser_name + "\nItem Rating: " + temp + "\nComment: " + review.getText() +"\nSeller Name: "+ final_sellerName);
                            review.setText("");
                            ratingBar.setRating(0);
                            rateCount.setText("");
                        }
                    }
                    }
                }
            }
        });
    }

    private Boolean valid() {
        boolean result = false;
        rev = review.getText().toString();
        r_count= rateCount.getText().toString();

        if (rev.isEmpty() || r_count.isEmpty()) {
            Toast.makeText(this, "Enter all the required details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

    private Boolean valid1(){
        boolean result= false;
        r_ser_name= s;

        if(r_ser_name.equals("Select Category")){
            Toast.makeText(this, "Select any Category First", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }
    private Boolean validSellerName(){
        boolean result= false;
        final_sellerName= s1;

        if(final_sellerName.isEmpty()){
            Toast.makeText(this, "Select any Seller First", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddFeedback.this, BuyerHome.class));
        finish();
    }
}