package com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.ui.CallRecorder;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.ui.NumberCallFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(R.id.mnuCall);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                display(item.getItemId());
                return true;
            }
        });
    }

    void display(int id) {
        Fragment frag = null;
        switch (id) {
            case R.id.mnuSave:
                frag = new NumberCallFragment();
                break;
            case R.id.mnuCall:
                frag = new CallRecorder();
                break;


        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, frag);
        ft.commit();

    }
}