package com.example.android.finalproject.Controller;

import android.content.Intent;
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

public class Home extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;

    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

                        Toast.makeText(getApplicationContext(), "Change Password", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.profile:

                        Toast.makeText(getApplicationContext(), "Change Profile", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.about:

                        Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.help:

                        Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.logout:

                        Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
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
}

