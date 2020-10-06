package com.example.furnitureproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Form extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login Form");

        txtEmail = (EditText)findViewById(R.id.etEmail1);
        txtPassword = (EditText)findViewById(R.id.pPwd1);
        btn_login = (Button) findViewById(R.id.btlogin);

        firebaseAuth =FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = txtEmail.getText().toString().trim();
                String Password = txtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(Login_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(Login_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Password.length()<8){
                    Toast.makeText(Login_Form.this, "Password short", Toast.LENGTH_SHORT).show();

                }


                firebaseAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                } else {
                                    Toast.makeText(Login_Form.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });





            }
        });


    }


    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(), Signup_Form.class));
    }
}
