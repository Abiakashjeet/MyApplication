package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    FragmentPagerAdapter adapterViewPager;
    ViewPager vpPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         vpPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tablayout = (TabLayout) findViewById(R.id.tab_layout);
        tablayout.setupWithViewPager(vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("position");
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(broadcastReceiver,intentFilter);

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }


            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("resived",""+1);
             String  value = intent.getStringExtra("value");
                 vpPager.setCurrentItem(Integer.parseInt(value));



            // get all your data from intent and do what you want

        }
    };
    public class broadcastReceived extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String actionGet = intent.getAction();
            Log.e("IntentNextPage","Received");

            if(actionGet.equals("nextPage")){

            }
        }
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        List<String> titles = Arrays.asList("Welcome", "House", "Flights", "Car", "Results");

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return 5;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return WelcomeFragment.newInstance();
                case 1:
                    return new HomeFragment();
                case 2:
                    return new FliGhtFragment();
                case 3:
                    return new CarFragment();
                case 4:
                    return new ResultFragment();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

    }
}