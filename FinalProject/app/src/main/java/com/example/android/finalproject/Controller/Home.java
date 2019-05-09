package com.example.android.finalproject.Controller;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.android.finalproject.R;
import com.example.android.finalproject.View.QuizView;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }



    public void playGame(View view)
    {
        //Intent to Quiz
        Intent playQuiz = new Intent(Home.this, Quiz.class);
        startActivity(playQuiz);
    }

    public void quitGame(View view)
    {
        //Exits game
        finish();
        moveTaskToBack(true);
    }
    }

