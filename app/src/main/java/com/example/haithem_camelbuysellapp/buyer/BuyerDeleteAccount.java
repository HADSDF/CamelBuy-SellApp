package com.example.haithem_camelbuysellapp.buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserInformation;
import com.example.haithem_camelbuysellapp.UserType;
import com.example.haithem_camelbuysellapp.seller.SellerDeleteAccount;
import com.example.haithem_camelbuysellapp.seller.SellerHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BuyerDeleteAccount extends AppCompatActivity {
    private TextView u_fname, u_phone, u_add;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private Button del_acc;
    NetworkInfo nInfo;
    private EditText d_email, d_password;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    String password1, email1;
    public static final String SHARED_PREFS= "sharedPrefs";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_delete_account);

        firebaseAuth = FirebaseAuth.getInstance();
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();

        firebaseDatabase = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Profile");

        del_acc = (Button) findViewById(R.id.del_acc1);
        u_fname = (TextView) findViewById(R.id.u_fname1);
        u_phone = (TextView) findViewById(R.id.u_phone1);
        u_add = (TextView) findViewById(R.id.u_add1);

        d_email = findViewById(R.id.d_email1);
        d_password = findViewById(R.id.d_password1);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation userInfo = dataSnapshot.getValue(UserInformation.class);
                u_fname.setText("UserName: " +userInfo.getUserName());
                u_phone.setText("Phone no.: "+userInfo.getUserPhone());
                u_add.setText("Address: "+userInfo.getUserAddress());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BuyerDeleteAccount.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


        del_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()){
                    //ref.getRef().removeValue();
                    delete_user();
                }
            }
        });
    }

    private void delete_user() {
        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(email1, password1);
        if(nInfo!=null && nInfo.isConnected()) {
            if (user1 != null) {
                user1.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                user1.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    ref.getRef().removeValue();
                                                    Log.d("TAG", "User account deleted");
                                                    Toast.makeText(BuyerDeleteAccount.this, "Account Deleted,", Toast.LENGTH_LONG).show();
                                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                                    SharedPreferences.Editor editor= sharedPreferences.edit();
                                                    editor.clear();
                                                    editor.commit();
                                                    firebaseAuth.signOut();
                                                    startActivity(new Intent(BuyerDeleteAccount.this, UserType.class));
                                                    finish();

                                                }
                                            }
                                        });
                            }
                        });
            }
        }
    }

    private Boolean valid(){
        boolean result= false;
        password1= d_password.getText().toString();
        email1= d_email.getText().toString();

        if(password1.isEmpty() || email1.isEmpty()){
            Toast.makeText(this, "Please enter email & password both", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(BuyerDeleteAccount.this, BuyerHome.class));
        finish();
    }
}