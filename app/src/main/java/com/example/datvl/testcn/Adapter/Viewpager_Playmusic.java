package com.example.datvl.testcn.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Viewpager_Playmusic extends FragmentPagerAdapter{
    public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    public Viewpager_Playmusic(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addfragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
