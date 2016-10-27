package com.yss.weixin;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yss.com.yss.utill.FaxianFragment;
import com.yss.com.yss.utill.TongxinluFragment;
import com.yss.com.yss.utill.WeiXinFragment;
import com.yss.com.yss.utill.WodeFarament;
import com.yss.fragmenttest.R;

/**
 * Created by Administrator on 2016/10/27.
 */
public class WeiXinActivity extends Activity {
    RadioGroup radioGroup;
    FragmentManager fragmentManager;
    Fragment wexinFragment;
    Fragment tongxunFragment;
    Fragment faxianFragment;
    Fragment wodeFragment;
    RadioButton weixinRB;
    RadioButton tongxunRB;
    RadioButton faxianRb;
    RadioButton wodeRB;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixin_layout);
        radioGroup= (RadioGroup) findViewById(R.id.weixin_radiogroup);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        weixinRB= (RadioButton) findViewById(R.id.weixin_btn);
        tongxunRB= (RadioButton) findViewById(R.id.tongxunlu_btn);
        faxianRb= (RadioButton) findViewById(R.id.faxian_btn);
        wodeRB= (RadioButton) findViewById(R.id.my_btn);
        layout = (LinearLayout) findViewById(R.id.content_btn);
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
        fragmentManager=getFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.content_btn,wexinFragment);
        transaction.commit();
    }
    public void addTongXun(){
        tongxunFragment=new TongxinluFragment();
        fragmentManager=getFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.content_btn,tongxunFragment);
        transaction.commit();
    }
    public void addFaXian(){
        faxianFragment=new FaxianFragment();
        fragmentManager=getFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.content_btn,faxianFragment);
        transaction.commit();
    }
    public void addWoDe(){
        wodeFragment=new WodeFarament();
        fragmentManager=getFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.content_btn,wodeFragment);
        transaction.commit();
    }
}
