package com.example.signupsigninfirebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity {
       private Button englishButton,banglaButton;
    private FirebaseAuth mAuth;
    private EditText mEmail,mPassword;
    private Button logIn;
    private TextView mForgetPassword;
    private  TextView mSignUpHere;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        englishButton=findViewById(R.id.englishButtonId);
        banglaButton=findViewById(R.id.banglaButtonId);
        progressBar=findViewById(R.id.progressbarId);
        mAuth = FirebaseAuth.getInstance();


        logIndetails();
    }
    private  void logIndetails(){

        mEmail=findViewById(R.id.emailEditTextLogInId);
        mPassword=findViewById(R.id.passwordEditTextLogInId);
        mForgetPassword=findViewById(R.id.forgetPassId);
        mSignUpHere=findViewById(R.id.signUpResId);
        logIn=findViewById(R.id.buttonLogInId);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email Required...");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password Required...");
                    return;
                }
                if (password.length()<6){
                    mPassword.setError("Minimum length of password should be 6");
                    mPassword.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()){
                            finish();
                            Toast.makeText(getApplicationContext(), "Log In is successful", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),ProfileActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);


                        }else{
                            Toast.makeText(getApplicationContext(),"LogIn Failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        //Registration Activity
        mSignUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });
//Reset  password Activity




    }
}
