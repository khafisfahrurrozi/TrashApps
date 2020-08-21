package com.example.trashapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button submit,signup,facebook,google;
    EditText email,passwordup;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    submit=findViewById(R.id.submit);
    signup=findViewById(R.id.signup);
    fAuth=FirebaseAuth.getInstance();
    facebook=findViewById(R.id.facebook);
    google=findViewById(R.id.google);
    email=findViewById(R.id.email);
    passwordup=findViewById(R.id.password);
    progressBar=findViewById(R.id.progressBar);


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    // Initialize Firebase Auth
        Toast.makeText(Login.this,"Firebase connect is success",Toast.LENGTH_LONG).show();
    /////////// SetOnListener

    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gohome();
//            String demail = email.getText().toString().trim();
//            String dpassword = passwordup.getText().toString().trim();
//
//            if(TextUtils.isEmpty(demail)){
//                email.setError("Email is Required.");
//                return;
//            }
//
//            if(TextUtils.isEmpty(dpassword)){
//                passwordup.setError("Password is Required.");
//                return;
//            }
//
//            if(dpassword.length() < 6){
//                passwordup.setError("Password Must be >= 6 Characters");
//                return;
//            }
//
//            progressBar.setVisibility(View.VISIBLE);
//
//            // authenticate the user
//
//            fAuth.signInWithEmailAndPassword(demail,dpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()){
//                        Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                    }else {
//                        Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);
//                    }
//
//                }
//            });

        }

    });

    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gosignup();
        }
    });
    }
    public  void gohome(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void  gosignup(){
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }


    private void ProcessLogin(){

    }
}
