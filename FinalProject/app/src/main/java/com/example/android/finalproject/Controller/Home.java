package com.example.android.finalproject.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.finalproject.Model.Common;
import com.example.android.finalproject.Model.Question;
import com.example.android.finalproject.R;
import com.example.android.finalproject.View.QuizView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.example.android.finalproject.Model.Common.questionList;

public class Home extends AppCompatActivity {

    Button btnPlay;
    FirebaseDatabase database;
    DatabaseReference questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("/questions");

        loadQuestion();

    }

    public void playGame(View view) {
        //Intent to Quiz
        Intent playQuiz = new Intent(Home.this, Quiz.class);
        startActivity(playQuiz);
    }

    public void quitGame(View view) {
        //Exits game
        finish();
        moveTaskToBack(true);
    }

    private void loadQuestion() {

        if(questionList.size() > 0)
            questionList.clear();

        questions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Question que = snapshot.getValue(Question.class);
                    que.setId(snapshot.getKey());
                    questionList.add(que);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //Random List
        Collections.shuffle(questionList);
    }
}

