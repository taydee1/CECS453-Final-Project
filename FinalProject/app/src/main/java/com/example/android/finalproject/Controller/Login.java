package com.example.android.finalproject.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.finalproject.R;
import com.example.android.finalproject.View.AdminMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


//Activity to log in a user or redirects them to a register form
public class Login extends AppCompatActivity {

    //Instantiating views
    EditText loginEmail_editText;
    EditText loginPassword_editText;
    Button loginBtn;

    //Declaring an instance of FirebaseAuth
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Casting Views
        loginEmail_editText = findViewById(R.id.loginEmail);
        loginPassword_editText = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);

        //Initializing the FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();


        //Button Click Event Handler
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = loginEmail_editText.getText().toString();
                String password = loginPassword_editText.getText().toString();

                //Checking if email field is empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Checking if password field is empty
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

               /*Creates a signIn method which takes in an email address and password, validates
               them , and signs in a user with the signInWithEmailandPassword method
               */
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //If login is successful, toast displays
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Home.class));

                                } else {
                                    //If login fails successful, toast displays
                                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }

    //Button to click if the user wants to register an account
    public void Register(View view) {
        //Intent to register form
        Intent register = new Intent(Login.this, Register.class);
        startActivity(register);
    }

    public void Temp(View view) {
        //Intent to register form
        Intent temp = new Intent(Login.this,  AdminMenu.class);
        startActivity(temp);
    }
}


