package com.example.android.finalproject.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.finalproject.R;
import com.example.android.finalproject.View.AdminMenu;
import com.google.firebase.auth.FirebaseAuth;

import java.util.prefs.Preferences;

public class BaseActivity extends AppCompatActivity {

    public Toolbar toolbar;


    ActionBarDrawerToggle mDrawerToggle;
    Context context;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    protected boolean useToolbar() {
        return true;
    }


    @Override
    public void setContentView(int layoutResID) {
        context = this;

        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.drawer_main, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.frame);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        super.setContentView(fullView);
        toolbar = (Toolbar) fullView.findViewById(R.id.tool_bar);




        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case R.id.home:
                        Intent home = new Intent(BaseActivity.this, Home.class);
                        startActivity(home);
                        break;

                    case R.id.password:

                        Intent changePassword = new Intent(BaseActivity.this, Password.class);
                        startActivity(changePassword);
                        break;

                    case R.id.profile:
                        Intent profilePage = new Intent(BaseActivity.this, ChangeProfile.class);
                        startActivity(profilePage);

                        break;

                    case R.id.about:
                        Intent aboutPage = new Intent(BaseActivity.this, AboutPage.class);
                        startActivity(aboutPage);

                        break;

                    case R.id.help:
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "admin@admin.com", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Help");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        break;

                    case R.id.logout:

                        firebaseAuth.getInstance().signOut();
                        Intent logout = new Intent(BaseActivity.this, Login.class);
                        startActivity(logout);
                        finish();
                        break;


                    default:

                        return true;
                }
                return true;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);


        firebaseAuth = FirebaseAuth.getInstance();
//        loadQuestion();

        //check if admin uid
        String admin_ = "RwobSJTRjkP3MDi1ejcf9lcCHex1";
        try{
            if(firebaseAuth.getUid().equals(admin_)){
                Menu menu = navigationView.getMenu();
                menu.add("Admin Menu").setIcon(R.drawable.admin).setOnMenuItemClickListener(
                        new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Intent admin = new Intent(context, AdminMenu.class);
                                startActivity(admin);
                                return true;
                            }
                        }
                );
            }
        }catch (Exception e){
            //if current user is null
            Log.e("error", e.getMessage());
        }


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return mDrawerToggle.onOptionsItemSelected(item);
    }
}

