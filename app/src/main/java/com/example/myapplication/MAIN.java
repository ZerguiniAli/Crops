package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MAIN extends AppCompatActivity {

    BottomNavigationView navigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        navigationView = findViewById(R.id.navigation);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null ;

                int itemId = item.getItemId();

                if(itemId == R.id.field)
                {
                    fragment = new map_selector();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.body, fragment).commit();



                return true;
            }
        });
    }
}