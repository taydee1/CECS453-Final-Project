package com.example.android.finalproject.Controller;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.finalproject.Model.User;
import com.example.android.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.finalproject.Model.User;
import com.example.android.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChangeProfile extends BaseActivity {
    EditText name;
    EditText user;
    EditText Email;
    Button changeProfileButton;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        name = findViewById(R.id.name);
        user = findViewById(R.id.username);
        changeProfileButton = findViewById(R.id.changeProfileButton);

        firebaseAuth = FirebaseAuth.getInstance();

        changeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = name.getText().toString();
                String userName = user.getText().toString();

                if (TextUtils.isEmpty(fullName))
                {
                    Toast.makeText(ChangeProfile.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();

                }

                if (TextUtils.isEmpty(userName))
                {
                    Toast.makeText(ChangeProfile.this, "Please Enter User Name", Toast.LENGTH_SHORT).show();

                }


                updateUser(fullName, userName);


            }
        });




    }


    private void updateUser(String fullName, String username) {

        User information = new User(fullName, username);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("User")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        database.child("fullName").setValue(fullName);
        database.child("userName").setValue(username);

        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();

        Intent homePage = new Intent(ChangeProfile.this, Home.class);
        startActivity(homePage);

    }
}
