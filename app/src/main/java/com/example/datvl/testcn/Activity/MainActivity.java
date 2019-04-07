package com.example.datvl.testcn.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.datvl.testcn.Adapter.MainViewPagerAdapter;
import com.example.datvl.testcn.Fragment.Fragment_Mv;
import com.example.datvl.testcn.Fragment.Fragment_Tim_Kiem;
import com.example.datvl.testcn.Fragment.Fragment_Trang_Chu;

import com.example.datvl.testcn.R;


public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();

        init();
    }

    private void init() {

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "Trang Chu");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "Tim Kiem");
        mainViewPagerAdapter.addFragment(new Fragment_Mv(),"MV");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.house);
        tabLayout.getTabAt(1).setIcon(R.drawable.searchd);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconmv);
    }

    private void anhxa() {
        viewPager = findViewById(R.id.myViewPager);
        tabLayout = findViewById(R.id.myTabLayout);

    }

}
