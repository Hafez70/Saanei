package com.example.hmohamadi.ebooklibrary;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class Main_Activity extends AppCompatActivity
        implements
        Menu_Fragment.OnFragmentInteractionListener,
        Setting_Fragment.OnFragmentInteractionListener,
        BookList_Fragment.OnFragmentInteractionListener
{
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    Menu_Fragment fragment = new Menu_Fragment();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                case R.id.navigation_Setting: {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    Setting_Fragment fragment = new Setting_Fragment();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                case R.id.navigation_BookList: {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    BookList_Fragment fragment = new BookList_Fragment();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_BookList);
    }

    @Override
    public void onFragmentInteraction(String data) {

        if(data.contains("call_setting"))
        {
            navigation.setSelectedItemId(R.id.navigation_Setting);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
