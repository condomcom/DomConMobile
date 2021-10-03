package com.example.condom.navigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.condom.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static String EMAIL_KEY = "EMAIL_KEY";
    public static String PASSWORD_KEY = "PASSWORD_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        ActivitiesFragment activitiesFragment = new ActivitiesFragment();
        FavouritesFragment favouritesFragment = new FavouritesFragment();
        PerformanceFragment performanceFragment = new PerformanceFragment();
        SpeakerFragment speakerFragment = new SpeakerFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.speakers) {
                    setFragment(speakerFragment);
                    return true;
                }
                else if(id == R.id.performances) {
                    setFragment(performanceFragment);
                    return true;
                }
                else if (id == R.id.activities) {
                    setFragment(activitiesFragment);
                    return true;
                }
                else if(id == R.id.favourites){
                    setFragment(favouritesFragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_nav, fragment);
        fragmentTransaction.commit();
    }
}