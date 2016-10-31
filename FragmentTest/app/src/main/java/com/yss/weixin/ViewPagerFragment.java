package com.yss.weixin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yss.com.yss.utill.MyFragmentPagerAdpater;
import com.yss.fragmenttest.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class ViewPagerFragment extends FragmentActivity {
    RadioGroup radioGroup;
    android.support.v4.app.FragmentManager fragmentManager;
    Fragment wexinFragment;
    Fragment tongxunFragment;
    Fragment faxianFragment;
    Fragment wodeFragment;
    RadioButton weixinRB;
    RadioButton tongxunRB;
    RadioButton faxianRb;
    RadioButton wodeRB;
    ViewPager viewPager;
    ArrayList<Fragment> fragmentArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_weixin);
        viewPager= (ViewPager) findViewById(R.id.weixin_viewpager);
        weixinRB= (RadioButton) findViewById(R.id.weixin_btn);
        tongxunRB= (RadioButton) findViewById(R.id.tongxunlu_btn);
        faxianRb= (RadioButton) findViewById(R.id.faxian_btn);
        wodeRB= (RadioButton) findViewById(R.id.my_btn);

        radioGroup= (RadioGroup) findViewById(R.id.weixin_radiogroup);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

        fragmentArrayList=new ArrayList<Fragment>();

        wexinFragment=new WeiXinFragment();
        tongxunFragment=new TongxinluFragment();
        faxianFragment=new FaxianFragment();
        wodeFragment=new WodeFarament();

        fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction= fragmentManager.beginTransaction();

        transaction.add(R.id.weixin_viewpager,wexinFragment);
        transaction.add(R.id.weixin_viewpager,tongxunFragment);
        transaction.add(R.id.weixin_viewpager,faxianFragment);
        transaction.add(R.id.weixin_viewpager,wodeFragment);
        transaction.commit();

        fragmentArrayList.add(wexinFragment);
        fragmentArrayList.add(tongxunFragment);
        fragmentArrayList.add(faxianFragment);
        fragmentArrayList.add(wodeFragment);

        MyFragmentPagerAdpater myFragmentPagerAdpater=new MyFragmentPagerAdpater(getSupportFragmentManager(),fragmentArrayList);
        viewPager.setAdapter(myFragmentPagerAdpater);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            switch (position){
                case 0:
                    weixinRB.setChecked(true);
                    break;
                case 1:
                    tongxunRB.setChecked(true);
                    break;
                case 2:
                    faxianRb.setChecked(true);
                    break;
                case 3:
                    wodeRB.setChecked(true);
                    break;
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //RadioGroup监听事件
    RadioGroup.OnCheckedChangeListener onCheckedChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                //微信监听事件
                case R.id.weixin_btn:
                    weixinRB.setTextColor(getResources().getColor(R.color.green));
                    tongxunRB.setTextColor(getResources().getColor(R.color.black));
                    faxianRb.setTextColor(getResources().getColor(R.color.black));
                    wodeRB.setTextColor(getResources().getColor(R.color.black));
                 addWeiXin();
                    break;
                //通讯录监听事件
                case R.id.tongxunlu_btn:
                    weixinRB.setTextColor(getResources().getColor(R.color.black));
                    tongxunRB.setTextColor(getResources().getColor(R.color.green));
                    faxianRb.setTextColor(getResources().getColor(R.color.black));
                    wodeRB.setTextColor(getResources().getColor(R.color.black));
                    addTongXun();
                    break;
                //发现监听事件
                case R.id.faxian_btn:
                    weixinRB.setTextColor(getResources().getColor(R.color.black));
                    tongxunRB.setTextColor(getResources().getColor(R.color.black));
                    faxianRb.setTextColor(getResources().getColor(R.color.green));
                    wodeRB.setTextColor(getResources().getColor(R.color.black));
                  addFaXian();
                    break;
                //我的监听事件
                case R.id.my_btn:
                    weixinRB.setTextColor(getResources().getColor(R.color.black));
                    tongxunRB.setTextColor(getResources().getColor(R.color.black));
                    faxianRb.setTextColor(getResources().getColor(R.color.black));
                    wodeRB.setTextColor(getResources().getColor(R.color.green));
               addWoDe();
                    break;
            }
        }
    };
    //添加视图微信
    public void addWeiXin() {
       wexinFragment=new WeiXinFragment();
        fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.weixin_viewpager,wexinFragment);
        transaction.commit();
    }
    public void addTongXun(){
        tongxunFragment=new TongxinluFragment();
        fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.weixin_viewpager,tongxunFragment);
        transaction.commit();
    }
    public void addFaXian(){
        faxianFragment=new FaxianFragment();
        fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.weixin_viewpager,faxianFragment);
        transaction.commit();
    }
    public void addWoDe(){
        wodeFragment=new WeiXinFragment();
        fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.weixin_viewpager,wodeFragment);
        transaction.commit();
    }
}
