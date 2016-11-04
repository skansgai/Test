package com.yss.timeviewdemo.zhihudaynews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.yss.timeviewdemo.R;

/**
 * Created by Administrator on 2016/11/4.
 */
public class StartAppActivity extends Activity {
    TextView start;
    float screenWidth;
    float screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appstart_animation);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth=metric.widthPixels;
        screenHeight=metric.heightPixels;
        start= (TextView) findViewById(R.id.start);
        startApp();
    }
    public void startApp(){
        AnimationSet animationSet=new AnimationSet(this,null);
       /* AnimatorSet animatorSet=new AnimatorSet();
        ObjectAnimator aa=ObjectAnimator.ofFloat(start,"ScaleX",1.0f,1.5f);
        ObjectAnimator ab=ObjectAnimator.ofFloat(start,"ScaleY",1.0f,1.5f);
        animatorSet.setDuration(5000);
        animatorSet.play(aa).with(ab);
        animatorSet.start();*/
        ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,1.5f,1.0f,1.5f,screenWidth/2,screenHeight/2);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.5f,1.0f);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setDuration(5000);
        start.startAnimation(animationSet);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(StartAppActivity.this,ZhiHuActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
