package com.example.android.finalproject.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Password extends BaseActivity {
    Button submitButton;
    EditText sendEmail;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        sendEmail = findViewById(R.id.email);
        submitButton = findViewById(R.id.submit);
        firebaseAuth = FirebaseAuth.getInstance();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = sendEmail.getText().toString();

                if (email.equals(""))
                {
                    Toast.makeText(Password.this, "Enter your email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                firebaseAuth.getInstance().signOut();
                                Toast.makeText(Password.this, "Please Check Your Email To Change Your Password", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Password.this, Login.class));
                                finish();
                            }
                            else
                            {
                               String error = task.getException().getMessage();
                                Toast.makeText(Password.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });



    }
}