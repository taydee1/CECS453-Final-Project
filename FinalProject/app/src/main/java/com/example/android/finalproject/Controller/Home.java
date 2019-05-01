package com.example.android.finalproject.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.finalproject.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Quick Quiz");
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
        System.exit(1);
    }
    }

