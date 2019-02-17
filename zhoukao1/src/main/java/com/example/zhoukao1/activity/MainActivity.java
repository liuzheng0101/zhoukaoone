package com.example.zhoukao1.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.zhoukao1.R;
import com.example.zhoukao1.fragment.Fuben_Fragment;
import com.example.zhoukao1.fragment.Home_Fragment;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private ViewPager pager;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    pager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager=findViewById(R.id.pager);
        navigation=findViewById(R.id.navigation);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 1:return new Home_Fragment();
                    default:return new Fuben_Fragment();
                }
            }
            @Override
            public int getCount() {
                return 3;
            }
        });

        mTextMessage = (TextView) findViewById(R.id.message);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:navigation.setSelectedItemId(R.id.navigation_home);break;
                    case 1:navigation.setSelectedItemId(R.id.navigation_dashboard);break;
                    case 2:navigation.setSelectedItemId(R.id.navigation_notifications);break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
