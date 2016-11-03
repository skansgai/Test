package com.yss.frameanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.yss.fragmenttest.R;

/**
 * Created by Administrator on 2016/11/2.
 */
public class FrameAnimationActivity extends Activity {
    TextView frameAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_animation);
       createFrameAnimation();
    }
    public void createFrameAnimation(){
        AnimationSet animationSet=new AnimationSet(this,null);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //int screenWidth=dm.widthPixels;
        int screenHeight=dm.heightPixels;
        TranslateAnimation translateAnimation=new TranslateAnimation(0,0,0,screenHeight);
        translateAnimation.setFillAfter(true);
        translateAnimation.setRepeatMode(Animation.REVERSE);
//        translateAnimation.setRepeatCount(100);//重复次数
        translateAnimation.setDuration(10000);//动画持续时间
        animationSet.addAnimation(translateAnimation);
        frameAnimation= (TextView) findViewById(R.id.frame_animation_bg);
        frameAnimation.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable animationDrawable= (AnimationDrawable) frameAnimation.getBackground();
        frameAnimation.startAnimation(animationSet);
        animationDrawable.start();

    }
}
