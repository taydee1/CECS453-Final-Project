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
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.finalproject.R;
import com.example.android.finalproject.View.QuizView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;

    private NavigationView navigationView;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();


        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);


        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();


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
}

