package com.yss.viewpagerdemo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import utill.BannerModel;
import utill.ImageViewPagerAdapter;

/**
 * Created by Administrator on 2016/10/31.
 */
public class BaiduTuActvity extends Activity {
    RadioGroup layout;
    ArrayList<View> arrayList;
    ArrayList<BannerModel> bannerModelArrayList;
    RadioButton c0;
    RadioButton c1;
    RadioButton c2;
    RadioButton c3;
    RadioButton c4;
    RadioButton c5;
    RadioButton c6;
    RadioButton c7;
    ViewPager viewPager;
    TextView textTitle;
    int itemId;
    String[] imgTitle={"你好啊！","吃饭了吗？","打豆豆吗！",
            "来玩啊","干嘛你","德玛西亚","hahaha!","神曲阿忆喔！"};
    int[] imgID={
            R.mipmap.ic_launcher
            ,R.mipmap.img1
            ,R.mipmap.img2
            ,R.mipmap.img3
            ,R.mipmap.img4
            ,R.mipmap.img5
            ,R.mipmap.img6
            ,R.mipmap.img7

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_viewpager);
        viewPager= (ViewPager) findViewById(R.id.baneer_viewpager);
        layout= (RadioGroup) findViewById(R.id.img_foot_layout);
        textTitle= (TextView) findViewById(R.id.text_title);
        c0= (RadioButton) findViewById(R.id.c0);
        c1= (RadioButton) findViewById(R.id.c1);
        c2= (RadioButton) findViewById(R.id.c2);
        c3= (RadioButton) findViewById(R.id.c3);
        c4= (RadioButton) findViewById(R.id.c4);
        c5= (RadioButton) findViewById(R.id.c5);
        c6= (RadioButton) findViewById(R.id.c6);
        c7= (RadioButton) findViewById(R.id.c7);
        arrayList=new ArrayList<View>();
        bannerModelArrayList=new ArrayList<BannerModel>();
        for(int i=0;i<imgID.length;i++){
            View view=getLayoutInflater().inflate(R.layout.viewpager_item,null);

            arrayList.add(view);
            BannerModel bannerModel=new BannerModel();
            bannerModel.setImgId(imgID[i]);
            bannerModel.setImageTitle(imgTitle[i]);
            bannerModelArrayList.add(bannerModel);
         /*   ImageView imageView=new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            imageView.setImageResource(imgID[i]);
            arrayList.add(imageView);*/
        }
        ImageViewPagerAdapter imageViewPagerAdapter=new ImageViewPagerAdapter(arrayList,bannerModelArrayList);
        viewPager.setAdapter(imageViewPagerAdapter);
        viewPager.setOnPageChangeListener(onPageChangeListener);
        Drawable drawable=getResources().getDrawable(R.drawable.circle_bule_style);
    }
    ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            itemId=position;
            Drawable drawable0=getResources().getDrawable(R.drawable.circle_bule_style);
            Drawable drawable1=getResources().getDrawable(R.drawable.circle_style);
            /*switch (position){
                case 0:
                    //textTitle.setText(imgTitle[0]);
                    c0.setBackground(drawable0);
                    c1.setBackground(drawable1);
                    c2.setBackground(drawable1);
                    c3.setBackground(drawable1);
                    c4.setBackground(drawable1);
                    c5.setBackground(drawable1);
                    c6.setBackground(drawable1);
                    c7.setBackground(drawable1);
                    break;
                case 1:
                   // textTitle.setText(imgTitle[1]);
                    c0.setBackground(drawable1);
                    c1.setBackground(drawable0);
                    c2.setBackground(drawable1);
                    c3.setBackground(drawable1);
                    c4.setBackground(drawable1);
                    c5.setBackground(drawable1);
                    c6.setBackground(drawable1);
                    c7.setBackground(drawable1);
                    break;
                case 2:
                   // textTitle.setText(imgTitle[2]);
                    c0.setBackground(drawable1);
                    c1.setBackground(drawable1);
                    c2.setBackground(drawable0);
                    c3.setBackground(drawable1);
                    c4.setBackground(drawable1);
                    c5.setBackground(drawable1);
                    c6.setBackground(drawable1);
                    c7.setBackground(drawable1);
                    break;
                case 3:
                    //textTitle.setText(imgTitle[3]);
                    c0.setBackground(drawable1);
                    c1.setBackground(drawable1);
                    c2.setBackground(drawable1);
                    c3.setBackground(drawable0);
                    c4.setBackground(drawable1);
                    c5.setBackground(drawable1);
                    c6.setBackground(drawable1);
                    c7.setBackground(drawable1);
                    break;
                case 4:
                   // textTitle.setText(imgTitle[4]);
                    c0.setBackground(drawable1);
                    c1.setBackground(drawable1);
                    c2.setBackground(drawable1);
                    c3.setBackground(drawable1);
                    c4.setBackground(drawable0);
                    c5.setBackground(drawable1);
                    c6.setBackground(drawable1);
                    c7.setBackground(drawable1);
                    break;
                case 5:
                   // textTitle.setText(imgTitle[5]);
                    c0.setBackground(drawable1);
                    c1.setBackground(drawable1);
                    c2.setBackground(drawable1);
                    c3.setBackground(drawable1);
                    c4.setBackground(drawable1);
                    c5.setBackground(drawable0);
                    c6.setBackground(drawable1);
                    c7.setBackground(drawable1);
                    break;
                case 6:
                   // textTitle.setText(imgTitle[6]);
                    c0.setBackground(drawable1);
                    c1.setBackground(drawable1);
                    c2.setBackground(drawable1);
                    c3.setBackground(drawable1);
                    c4.setBackground(drawable1);
                    c5.setBackground(drawable1);
                    c6.setBackground(drawable0);
                    c7.setBackground(drawable1);
                    break;
                case 7:
                   // textTitle.setText(imgTitle[7]);
                    c0.setBackground(drawable1);
                    c1.setBackground(drawable1);
                    c2.setBackground(drawable1);
                    c3.setBackground(drawable1);
                    c4.setBackground(drawable1);
                    c5.setBackground(drawable1);
                    c6.setBackground(drawable1);
                    c7.setBackground(drawable0);
                    break;
            }*/
        }
        @Override
        public void onPageSelected(int position) {
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
    /*RadioGroup.OnCheckedChangeListener onClickListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    };*/
   /* //耗时自动切换
    public void threads() {
        Thread thread = new Runnable() {
            @Override
            public void run() {

            }
        }*/
}
