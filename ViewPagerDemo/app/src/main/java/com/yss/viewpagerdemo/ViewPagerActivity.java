package com.yss.viewpagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import utill.MyViewPagerAdapter;

/**
 * Created by Administrator on 2016/10/31.
 */
public class ViewPagerActivity extends Activity {
    ViewPager viewPager;
    ArrayList<View> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        arrayList=new ArrayList<View>();
        for(int i=0;i<10;i++){
            TextView textView=new TextView(this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText("nihao"+i);
            arrayList.add(textView);
        }
        MyViewPagerAdapter myViewPagerAdapter=new MyViewPagerAdapter(arrayList);
        viewPager.setAdapter(myViewPagerAdapter);
    }
}
