package com.example.android.finalproject.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.android.finalproject.Model.Question;
import com.example.android.finalproject.R;
import com.example.android.finalproject.View.AdminMenu;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




import java.util.ArrayList;

public class Quiz extends AppCompatActivity {
    private DatabaseReference mDatabase;


    private TextView mScoreView;
    private TextView mQuestion;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private int count = 0;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 1;

    private Firebase mQuestionRef, mChoice1Ref, mChoice2Ref, mChoice3Ref, mChoice4Ref,
            mAnswerRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizview);

        mScoreView = (TextView) findViewById(R.id.score);
        mQuestion = (TextView) findViewById(R.id.question);

        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
    }
}


      //  updateQuestion();

//        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mButtonChoice1.getText().equals(mAnswer)) {
//                    mScore = mScore + 1;
//                    updateQuestion();
//                } else {
//                    updateQuestion();
//                }
//            }
//
//        });
//
//        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mButtonChoice2.getText().equals(mAnswer)) {
//                    mScore = mScore + 1;
//                    updateQuestion();
//                } else {
//                    updateQuestion();
//                }
//            }
//
//
//        });
//
//        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mButtonChoice3.getText().equals(mAnswer)) {
//                    mScore = mScore + 1;
//                    updateQuestion();
//                } else {
//                    updateQuestion();
//                }
//            }
//
//
//        });
//
//    }

//    public void updateQuestion() {
//
//        if (count == 2) {
//            Intent temp = new Intent(Quiz.this, Home.class);
//            startActivity(temp);
//        }
//        mQuestionRef = new Firebase("https://finalproject-c6f51.firebaseio.com/questions/" + mQuestionNumber + "/question");
//        mChoice2Ref = new Firebase("https://finalproject-c6f51.firebaseio.com/questions/" + mQuestionNumber + "/b");
//        mChoice3Ref = new Firebase("https://finalproject-c6f51.firebaseio.com/questions/" + mQuestionNumber + "/c");
//
//
//        mQuestionRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String question = dataSnapshot.getValue(String.class);
//                mQuestion.setText(question);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        mChoice1Ref = new Firebase("https://finalproject-c6f51.firebaseio.com/questions/" + mQuestionNumber + "/a");
//        mChoice1Ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String choice = dataSnapshot.getValue(String.class);
//                mButtonChoice1.setText(choice);
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        mChoice2Ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String choice = dataSnapshot.getValue(String.class);
//                mButtonChoice2.setText(choice);
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        mChoice3Ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String choice = dataSnapshot.getValue(String.class);
//                mButtonChoice3.setText(choice);
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        mAnswerRef = new Firebase("https://finalproject-c6f51.firebaseio.com/questions/" + mQuestionNumber + "/answer");
//        mAnswerRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                mAnswer = dataSnapshot.getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        mQuestionNumber++;
//        count++;
//    }
//
//    public void Temp(View view) {
//        //Intent to register form
//
//    }


    /*public void getQuestionCount() {

        mDatabase = FirebaseDatabase.getInstance().getReference("/questions");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Question que = snapshot.getValue(Question.class);
                    Log.d("index?: ", snapshot.getKey().toString());
                    Log.d("WHAAAA", snapshot.getValue().toString());
                    Log.d("FIREBAse QUESTION: ", que.toString());


                }
                Log.d("FIREBAse COUNT: ", "" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

}
*/