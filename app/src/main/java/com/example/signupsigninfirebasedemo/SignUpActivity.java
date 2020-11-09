package com.example.signupsigninfirebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText mEmail,mPassword;
    private Button mSignUP;
    private TextView mLogin;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.emailEditTextSignUpId);
        mPassword = findViewById(R.id.passwordEditTextSignUpId);
        mSignUP = findViewById(R.id.buttonSignUpId);

       mSignUP.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               switch (view.getId()){
                   case R.id.buttonSignUpId:
                       userRegister();
                       break;

                   case R.id.signInResId:
                       Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                       break;

               }
           }
       });
    }

    private void userRegister() {

        String email=mEmail.getText().toString().trim();
        String password=mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            mEmail.setError("Email required....");
            mEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Enter a vaild Email");
            mEmail.requestFocus();
            return;


        }
        if (TextUtils.isEmpty(password)){
            mPassword.setError("Password required....");
            mPassword.requestFocus();
            return;
        }

        if (password.length()<6){
            mPassword.setError("Minimum length of password should be 6");
            mPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"Registration is successful",Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(),"Registration is not successful",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    }
