package com.example.haithem_camelbuysellapp.seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserType;
import com.example.haithem_camelbuysellapp.buyer.BuyerLogin;
import com.example.haithem_camelbuysellapp.buyer.ForgetPassword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SellerLogin extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText p_email, p_password;
    private TextView sign, forgetP;
    private Button login;
    private FirebaseUser user;
    private ProgressDialog progressDialog;
    NetworkInfo nInfo;
    String ff1;
    public static final String SHARED_PREFS= "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        p_email= (EditText) findViewById(R.id.p_email2);
        p_password= (EditText) findViewById(R.id.p_password2);
        sign= (TextView) findViewById(R.id.sign2);
        login= (Button) findViewById(R.id.login2);
        forgetP= (TextView) findViewById(R.id.forget1);
        forgetP.setPaintFlags(forgetP.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        user= firebaseAuth.getCurrentUser();

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();

        //to keep user logged in this method calll

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    if(nInfo!=null && nInfo.isConnected()) {
                        progressDialog.setMessage("Wait for the authentication approval");
                        progressDialog.show();
                        String ema_user = p_email.getText().toString().trim();
                        String pas_user = p_password.getText().toString().trim();
                        firebaseAuth.signInWithEmailAndPassword(ema_user, pas_user).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.dismiss();

                                    //to keep user logged in
                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                    SharedPreferences.Editor editor= sharedPreferences.edit();
                                    editor.putString("name", "seller");
                                    editor.apply();
                                    //end

                                    Toast.makeText(SellerLogin.this, "Successful Login", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SellerLogin.this, SellerHome.class));
                                    finish();

                                } else {
                                    Toast.makeText(SellerLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(SellerLogin.this, "Network is not available", Toast.LENGTH_LONG).show();}

                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerLogin.this, SellerRegistration.class));
                finish();
            }
        });

        forgetP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerLogin.this, ForgetPassword1.class));
                finish();
            }
        });

    }
    private Boolean valid(){
        boolean result= false;
        String password= p_email.getText().toString();
        String email= p_password.getText().toString();

        if(password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Fill both options", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SellerLogin.this, UserType.class));
        finish();
    }
}