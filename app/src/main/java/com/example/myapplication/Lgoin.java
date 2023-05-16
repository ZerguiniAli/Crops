package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Lgoin extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgoin);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        bottomNavigationView = findViewById(R.id.switchlogin);

        getSupportFragmentManager().beginTransaction().replace(R.id.switchlayout ,new Login()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                int itemId = item.getItemId();

                if (itemId == R.id.login) {
                    fragment = new Login();
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,
                                    R.anim.slide_in_right, R.anim.slide_out_left)
                            .replace(R.id.switchlayout, fragment)
                            .addToBackStack(null)
                            .commit();
                } else if (itemId == R.id.Create_account) {
                    fragment = new Create_account();
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                                    R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.switchlayout, fragment)
                            .addToBackStack(null)
                            .commit();
                }

                return true;
            }
        });

    }
}