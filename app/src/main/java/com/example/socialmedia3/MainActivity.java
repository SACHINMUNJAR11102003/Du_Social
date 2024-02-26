package com.example.socialmedia3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.socialmedia3.fragment.AddFragment;
import com.example.socialmedia3.fragment.HomeFragment;
import com.example.socialmedia3.fragment.MakeNewConnectionFragment;
import com.example.socialmedia3.fragment.NotificationFragment;
import com.example.socialmedia3.fragment.ProfileFragment;
import com.example.socialmedia3.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    ImageView makeNewConnection_Imageview;
    NavigationView navigationView;
    ImageView open_navView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        makeNewConnection_Imageview=findViewById(R.id.makeNewConnection_Imageview_mainActivity);
        navigationView=findViewById(R.id.navigation_view_mainActivity);
        open_navView=findViewById(R.id.open_navView_button);

        loadFragment2(new HomeFragment());

        open_navView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationView.setVisibility(View.VISIBLE);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.logout:
                        logoutUser();
                        return true;
                    default:
                        return false;
                }
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        loadFragment2(new HomeFragment());
                        return true;
                    case R.id.navigation_add:
                        loadFragment2(new AddFragment());
                        return true;
                    case R.id.navigation_notification:
                        loadFragment2(new NotificationFragment());
                        return true;
                    case R.id.navigation_profile:
                        loadFragment2(new ProfileFragment());
                        return true;
                    case R.id.navigation_search:
                        loadFragment2(new SearchFragment());
                        return true;

                    default:
                        return false;
                }
            }
        });



        makeNewConnection_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment2(new MakeNewConnectionFragment());
            }
        });


    }
    public void loadFragment2(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMainActivity,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    

    public void logoutUser(){
        FirebaseAuth.getInstance().signOut();
    }


    }

