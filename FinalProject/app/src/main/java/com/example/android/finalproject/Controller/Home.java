package com.example.android.finalproject.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.finalproject.Model.Question;
import com.example.android.finalproject.R;
import com.example.android.finalproject.View.AdminMenu;
import com.example.android.finalproject.View.QuizView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Collections;

import static com.example.android.finalproject.Model.Common.questionList;

public class Home extends BaseActivity {
    Button btnPlay;
    FirebaseDatabase database;
    DatabaseReference questions;



    FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        database = FirebaseDatabase.getInstance();
        questions = database.getReference("/questions");

        firebaseAuth = FirebaseAuth.getInstance();
        loadQuestion();

    }



    //Method that redirects user to the actual playing of the quiz
    public void playGame(View view) {
        //Intent to Quiz

         Intent playQuiz = new Intent(Home.this, Quiz.class);
        try {
            startActivity(playQuiz);
        } catch (Exception e) {
//            finish();
//            startActivity(getIntent());
        }

    }

        //method that will quit the game once button is clicked
        public void quitGame (View view){
            //Exits game
            finish();
            moveTaskToBack(true);
        }

        //Prohibits user from using the back button on this page
        @Override
        public void onBackPressed ()
        {

        }

        private void loadQuestion () {

            if (questionList.size() > 0)
                questionList.clear();

            questions.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Question que = snapshot.getValue(Question.class);
                        que.setId(snapshot.getKey());
                        questionList.add(que);
                    }
                    //Generates Random List
                    Collections.shuffle(questionList);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }


        public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

            Activity activity;

            public DefaultExceptionHandler(Activity activity) {
                this.activity = activity;
//            Context context = getApplicationContext();
//            CharSequence text = "Sorry, Could n";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
                Context context = getApplicationContext();
                CharSequence text = "Sorry, Could not connect to server!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            @Override
            public void uncaughtException(Thread thread, final Throwable ex) {

                Intent intent = new Intent(activity, Home.class);
                Log.d("ERROR", "---------" + ex.getMessage());
                Log.d("ERROR", "--------" + ex.getCause());
                Log.d("ERROR", "--------" + Arrays.toString(ex.getStackTrace()));
                activity.startActivity(intent);
                activity.finish();

                System.exit(0);

            }


        }

    }


