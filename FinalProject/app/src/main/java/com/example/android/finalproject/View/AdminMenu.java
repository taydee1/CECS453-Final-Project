package com.example.android.finalproject.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.android.finalproject.Controller.Home;
import com.example.android.finalproject.R;

public class AdminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
//        getSupportActionBar().setTitle("Administrator Menu");
    }

    //Button to click if the user wants to register an account
    public void AddQuestion(View view) {
        //Intent to register form
        Intent register = new Intent(AdminMenu.this, NewQuestionActivity.class);
        startActivity(register);
    }

    public void DisplayAll(View view) {
        //Intent to register form
        Intent register = new Intent(AdminMenu.this, ViewDelete.class);
        startActivity(register);
    }

    public void Home(View view) {
        //Intent to register form
        Intent register = new Intent(AdminMenu.this, Home.class);
        startActivity(register);
    }
}
