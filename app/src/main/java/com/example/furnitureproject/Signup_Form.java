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

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Signup_Form extends AppCompatActivity {
    EditText txtFull_Name, txtEmail, txtMobile_Number, txtPassword, txtConfirm_Password;
    Button btn_register;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup Form");

        txtFull_Name = (EditText)findViewById(R.id.etName);
        txtEmail = (EditText)findViewById(R.id.etEmail1);
        txtMobile_Number = (EditText) findViewById(R.id.nNumber);
        txtPassword = (EditText)findViewById(R.id.pPwd2);
        txtConfirm_Password = (EditText)findViewById(R.id.cp);
        btn_register = (Button)findViewById(R.id.btRegister);

        firebaseAuth =FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Full_Name = txtFull_Name.getText().toString().trim();
                String Email = txtEmail.getText().toString().trim();
                String Mobile_Number = txtMobile_Number.getText().toString().trim();
                String Password = txtPassword.getText().toString().trim();
                String Confirm_Password =  txtConfirm_Password.getText().toString().trim();

                if (TextUtils.isEmpty(Full_Name)){
                    Toast.makeText(Signup_Form.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Confirm_Password)){
                    Toast.makeText(Signup_Form.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Mobile_Number)){
                    Toast.makeText(Signup_Form.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Password.length()<8){
                    Toast.makeText(Signup_Form.this, "Password short", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (Password.equals(Confirm_Password)) {

                    firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Signup_Form.this, "Registration Complete", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(Signup_Form.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });















                }

            }
        });


    }
}
