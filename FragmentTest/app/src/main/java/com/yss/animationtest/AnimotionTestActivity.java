package com.yss.animationtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.yss.fragmenttest.R;

/**
 * Created by Administrator on 2016/11/1.
 */
public class AnimotionTestActivity extends Activity{
    private ImageView webImage;
    private AnimationSet animationSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        webImage= (ImageView) findViewById(R.id.web_img);
        //startAlphaAnimation();//渐变动画
       // startTranslateAnimation();//位移动画
        startRotateAnimation();//旋转动画
        //startScaleAnimation();//缩放动画
    }
    //创建透明度动画的方法
    public void startAlphaAnimation(){
        animationSet=new AnimationSet(this,null);
        AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.5f);
        alphaAnimation.setDuration(5000);//动画持续时间
        alphaAnimation.setRepeatCount(3);//重复次数
        animationSet.addAnimation(alphaAnimation);//添加到动画集合中
       // webImage.startAnimation(animationSet);//让控件开始执行动画集合中中的动画
    }
    //创建位移动画方法
    public void startTranslateAnimation(){
        animationSet=new AnimationSet(this,null);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth=dm.widthPixels;
        int screenHeight=dm.heightPixels;
       /* for(int i=0;i>0;i++){
        }*/
        TranslateAnimation translateAnimation=new TranslateAnimation(0,screenWidth,0,screenHeight);
        translateAnimation.setDuration(5000);//动画持续时间
        translateAnimation.setRepeatCount(3);//重复次数
        animationSet.addAnimation(translateAnimation);//添加到动画集合中
       // webImage.startAnimation(animationSet);//让控件开始执行动画集合中中的动画
    }
    //创建缩放动画方法
    public void startScaleAnimation(){
        ScaleAnimation scaleAnimation=new ScaleAnimation(1,5,1,5);
        scaleAnimation.setDuration(5000);//动画持续时间
        scaleAnimation.setRepeatCount(3);//重复次数
        animationSet.addAnimation(scaleAnimation);//添加到动画集合中
       // webImage.startAnimation(animationSet);//让控件开始执行动画集合中中的动画
    }
    //创建旋转动画方法
    public void startRotateAnimation(){
        animationSet=new AnimationSet(this,null);
        RotateAnimation rotateAnimation=new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(5000);//动画持续时间
        rotateAnimation.setRepeatCount(3);//重复次数
        animationSet.addAnimation(rotateAnimation);//添加到动画集合中
        webImage.startAnimation(animationSet);//让控件开始执行动画集合中中的动画
    }
}
