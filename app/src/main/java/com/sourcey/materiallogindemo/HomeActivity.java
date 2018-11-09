package com.sourcey.materiallogindemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    FragmentManager fm;
    public Fragment HomeFragment1;
    public Fragment mContent;//当前fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }
    private void init() {
        fm = getSupportFragmentManager();
        HomeFragment1 = new HomeFragment();


        mContent = HomeFragment1 ;
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, mContent);
        ft.commitAllowingStateLoss();
    }
}
