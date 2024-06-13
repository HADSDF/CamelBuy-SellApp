package com.example.haithem_camelbuysellapp.seller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.haithem_camelbuysellapp.R;
import com.example.haithem_camelbuysellapp.UserCamel;
import com.example.haithem_camelbuysellapp.UserInformation;
import com.example.haithem_camelbuysellapp.UserMeat;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddMeat extends AppCompatActivity {
    private Button upload, select;
    private int Request_Code= 234;
    private Uri filepath;
    private ImageView img;
    private EditText price, name, weight, detail;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private String u_price, u_name, u_detail, u_weight;
    private FirebaseAuth firebaseAuth;
    String s_name, s_phone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meat);

        upload=(Button) findViewById(R.id.upload1);
        select= (Button) findViewById(R.id.select1);

        price= (EditText) findViewById(R.id.price1);
        name= (EditText) findViewById(R.id.name1);
        weight= (EditText) findViewById(R.id.weight1);
        detail= (EditText) findViewById(R.id.detail1);
        img= (ImageView)findViewById(R.id.img1);

        firebaseAuth= FirebaseAuth.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Items").child(user.getUid()).child("meat");

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Profile");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation userInformation= dataSnapshot.getValue(UserInformation.class);
                s_name= userInformation.getUserName();
                s_phone=userInformation.getUserPhone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddMeat.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filepath!=null){
                    if (validate()){
                        upload();
                    }
                }
                else{
                    Toast.makeText(AddMeat.this, "Select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private Boolean validate(){
        boolean result= false;

        u_price= price.getText().toString();
        u_detail= detail.getText().toString();
        u_name= name.getText().toString();
        u_weight= weight.getText().toString();
        if(u_price.isEmpty() || u_detail.isEmpty() || u_name.isEmpty() || u_weight.isEmpty() ){
            Toast.makeText(AddMeat.this, "First add all details of Meat", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    private void showImageChooser(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== 1 && resultCode== RESULT_OK && data != null && data.getData() != null){
            filepath= data.getData();
            img.setImageURI(filepath);

        }
    }

    private String getExtension(Uri uri){
        ContentResolver cr= getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void upload() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference reference= storageReference.child("uploads/"+System.currentTimeMillis()+ "."+getExtension(filepath));

        reference.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri =taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url= uri.getResult();

                        UserMeat userMeat = new UserMeat(u_name, u_price, u_detail, u_weight, url.toString(), s_name, s_phone);
                        databaseReference.child(databaseReference.push().getKey()).setValue(userMeat);

                        progressDialog.dismiss();
                        Toast.makeText(AddMeat.this, "Meat details uploaded Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddMeat.this, SellerHome.class));
                        finish();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress= (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        progressDialog.setMessage("uploaded: " +(int)progress+" %");
                    }
                });

    }


    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        startActivity(new Intent(AddMeat.this, SellerSelectCategory.class));
        finish();
    }
}