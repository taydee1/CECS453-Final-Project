package com.example.android.finalproject.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.finalproject.Model.Common;
import com.example.android.finalproject.R;
import com.example.android.finalproject.ResultsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Quiz extends AppCompatActivity implements View.OnClickListener {

    final static long INTERVAL = 2000;
    final static long TIMEOUT = 8000;
    int progressValue = 0;

    CountDownTimer mCountDown;

    int index = 0, score = 0,
            thisQuestion = 0,
            totalQuestion,
            correctAnswer;

//    FirebaseDatabase database;
//    DatabaseReference questions;

    ProgressBar progressBar;
    Button btnA, btnB, btnC, btnD;
    TextView txtScore, txtQuestionNum, question_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        //Views
        txtScore = (TextView)findViewById(R.id.txtScore);
        txtQuestionNum = (TextView)findViewById(R.id.txtTotalQuestion);
        question_text = (TextView)findViewById(R.id.question_text);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnA = (Button)findViewById(R.id.btnAnswerA);
        btnB = (Button)findViewById(R.id.btnAnswerB);
        btnC = (Button)findViewById(R.id.btnAnswerC);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        mCountDown.cancel();
        if(index < totalQuestion-1) {
            Button clickedButton = (Button) view;
            if(clickedButton.getText().equals(Common.questionList.get(index).getAnswer())){
                score += 10;
                correctAnswer++;
                showQuestion(++index); // next Question

            }else{
                showQuestion(++index);
            }
//            else {
//                Intent intent = new Intent(this, ResultsActivity.class);
//                Bundle dataSend = new Bundle();
//                dataSend.putInt("SCORE", score);
//                dataSend.putInt("TOTAL", totalQuestion);
//                dataSend.putInt("CORRECT", correctAnswer);
//                intent.putExtras(dataSend);
//                startActivity(intent);
//                finish();
//            }
            txtScore.setText(String.format("%d", score));
        }
        else {
            /** Anytime the game is over, hand over data from quiz to results screen
             *   ResultsActivity
             *  **/
            Intent intent = new Intent(this, ResultsActivity.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE", score);
            dataSend.putInt("TOTAL", totalQuestion);
            dataSend.putInt("CORRECT", correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }

    }

    private void showQuestion (int index){
        if (index < totalQuestion)
        {
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d", thisQuestion, totalQuestion));
            progressBar.setProgress(0);
            progressValue = 0;

            question_text.setText(Common.questionList.get(index).getQuestion());
            question_text.setVisibility(View.VISIBLE);

        }
        btnA.setText(Common.questionList.get(index).getA());
        btnB.setText(Common.questionList.get(index).getB());
        btnC.setText(Common.questionList.get(index).getC());

        mCountDown.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        totalQuestion = Common.questionList.size()-1;
        if(totalQuestion > 4){
            totalQuestion = 5;
        }

        mCountDown = new CountDownTimer(TIMEOUT, INTERVAL) {
            @Override
            public void onTick(long minisec) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);

            }
        };
        showQuestion(index);
    }
}


