package com.yss.com.yss.utill;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class MyFragmentPagerAdpater extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList;
    public MyFragmentPagerAdpater(FragmentManager fragmentManager,ArrayList<Fragment> fragmentArrayList){
        super(fragmentManager);
        this.fragmentArrayList=fragmentArrayList;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
}
