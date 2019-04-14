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
import com.example.android.finalproject.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Register Activity to register a user or redirect them to login form if user has a login
public class Register extends AppCompatActivity {

    //Instantiating views
    Button regButton;
    EditText fullName_editText;
    EditText userName_editText;
    EditText email_editText;
    EditText password_edit_Text;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

        //Casting Views
        fullName_editText = findViewById(R.id.fullName);
        userName_editText = findViewById(R.id.userName);
        email_editText = findViewById(R.id.email);
        password_edit_Text = findViewById(R.id.password);
        regButton = findViewById(R.id.registerButton);


        //Writing a message to the database
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        //Event handler when register button is clicked
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Converting EditText to Strings
                final String fullName = fullName_editText.getText().toString();
                final String userName = userName_editText.getText().toString();
                final String email = email_editText.getText().toString();
                String password = password_edit_Text.getText().toString();

                //Checking if Full Name field is empty
                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(Register.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Checking if User Name field is empty
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(Register.this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
                    return;
                }


                /*Creating a new createAccount which takes in an email address and password
                validates them and then creates a new user with the createUserWithEmailAndPassword
                method
                 */
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Method to test if user sign up is successful
                                if (task.isSuccessful()) {

                                    //Creating a user instance
                                    User information = new User(fullName, userName, email);

                                    //Writes the user data to database
                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            //Toast which displays if registration is successful and redirects user to Quiz activity
                                            Toast.makeText(Register.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), Quiz.class));
                                        }
                                    });

                                } else {

                                    //Toast displayed if registering user field
                                    Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void Login(View view)
    {
        //Intent to Login form
        Intent login = new Intent(Register.this, Login.class);
        startActivity(login);
    }
}
