package com.example.android.finalproject.Controller;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.finalproject.Model.Question;
import com.example.android.finalproject.R;
import com.example.android.finalproject.View.QuizView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

import static com.example.android.finalproject.Model.Common.questionList;

public class Home extends AppCompatActivity {
    Button btnPlay;
    FirebaseDatabase database;
    DatabaseReference questions;

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;

    private NavigationView navigationView;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("/questions");

        firebaseAuth = FirebaseAuth.getInstance();

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);


        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        loadQuestion();



        navigationView = (NavigationView) findViewById(R.id.nv);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id) {

                    case R.id.password:

                        Intent changePassword = new Intent(Home.this, Password.class);
                        startActivity(changePassword);
                        break;

                    case R.id.profile:
                        Intent profilePage = new Intent(Home.this, ChangeProfile.class);
                        startActivity(profilePage);

                        break;

                    case R.id.about:
                        Intent aboutPage = new Intent(Home.this, AboutPage.class);
                        startActivity(aboutPage);

                        break;

                    case R.id.help:
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","admin@admin.com", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Help");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        break;

                    case R.id.logout:

                        firebaseAuth.getInstance().signOut();
                        Intent logout = new Intent(Home.this, Login.class);
                        startActivity(logout);
                        finish();
                        break;


                    default:

                        return true;

                }


                return true;


            }

        });


    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {


        if (toggle.onOptionsItemSelected(item))

            return true;

        return super.onOptionsItemSelected(item);

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

    @Override
    public void onBackPressed()
    {

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

