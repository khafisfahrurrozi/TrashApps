package com.example.trashapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    Button create;
    public static final String TAG = "TAG";
    getset sendata;
    EditText usernameup,passwordup,phone,email;
    DatabaseReference reff;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameup =findViewById(R.id.usernameup);
        passwordup=findViewById(R.id.password);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        create = findViewById(R.id.create);


        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
//        progressBar=findViewById(R.id.progressBar);
        reff= FirebaseDatabase.getInstance().getReference().child("getset");

        sendata= new getset();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                gohome();
                Long phn = Long.parseLong(phone.getText().toString().trim());
                sendata.setUsername(usernameup.getText().toString().trim());
                sendata.setEmail(email.getText().toString().trim());
                sendata.setPhone(phn);
                sendata.setPasswordup(passwordup.getText().toString().trim());
                reff.push().setValue(sendata);
                Toast.makeText(SignUp.this,"Sign Up Success",Toast.LENGTH_LONG).show();
//
//                final String demail = email.getText().toString().trim();
//                String dpassword = passwordup.getText().toString().trim();
//                final String dusername = usernameup.getText().toString();
//                final String dphone   = phone.getText().toString();
//
//                if(TextUtils.isEmpty(demail)){
//                    email.setError("Email is Required.");
//                    return;
//                }
//
//                if(TextUtils.isEmpty(dpassword)){
//                    passwordup.setError("Password is Required.");
//                    return;
//                }
//
//                if(dpassword.length() < 6){
//                    passwordup.setError("Password Must be >= 6 Characters");
//                    return;
//                }
//
//                progressBar.setVisibility(View.VISIBLE);
//
//                // register the user in firebase
//
//                fAuth.createUserWithEmailAndPassword(demail,dpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//
//                            // send verification link
//
//                            FirebaseUser fuser = fAuth.getCurrentUser();
//                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Toast.makeText(SignUp.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
//                                }
//                            });
//
//                            Toast.makeText(SignUp.this, "User Created.", Toast.LENGTH_SHORT).show();
//                            userID = fAuth.getCurrentUser().getUid();
//                            DocumentReference documentReference = fStore.collection("users").document(userID);
//                            Map<String,Object> user = new HashMap<>();
//                            user.put("fName",dusername);
//                            user.put("email",demail);
//                            user.put("phone",dphone);
//                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.d(TAG, "onFailure: " + e.toString());
//                                }
//                            });
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//
//                        }else {
//                            Toast.makeText(SignUp.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//                });

            }
        });
    }

    public  void gohome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
