package com.yss.propertyanimator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yss.fragmenttest.R;

/**
 * Created by Administrator on 2016/11/2.
 */
public class ValueAnimatorActivity extends Activity {
    TextView animationBg;
    TextView circleOne;
    TextView circleTwo;
    TextView circleThree;
    TextView circleFour;
    TextView circleFive;
    TextView colorText;
    Button animatorBtn;
    AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_animation);
        animationBg= (TextView) findViewById(R.id.frame_animation_bg);
        animatorBtn= (Button) findViewById(R.id.animator_btn);

        circleOne= (TextView) findViewById(R.id.circle_one);
        circleTwo= (TextView) findViewById(R.id.circle_two);
        circleThree= (TextView) findViewById(R.id.circle_three);
        circleFour= (TextView) findViewById(R.id.circle_four);
        circleFive= (TextView) findViewById(R.id.circle_five);

        colorText= (TextView) findViewById(R.id.color_text);
        animatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startAnimatorSet();
                addAnimator();
            }
        });
    }
    public void startValueAnimator(){
        //valueAnimator是整个属性动画最核心的一个类，
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0f,40f);
        valueAnimator.setRepeatMode(Animation.RESTART);
        //设置动画持续时间
        valueAnimator.setDuration(400);
        //设置动画更新监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Toast.makeText(ValueAnimatorActivity.this,"更新动画了",Toast.LENGTH_SHORT).show();
            }
        });
//执行动画
        valueAnimator.start();
    }
    public void startObjectAnmator(){
        //需要改变的属性对象，属性名，起始值，结束值
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(animationBg,"ScaleX",1.0f,2.0f);
        //设置动画时间
        objectAnimator.setDuration(5000);
        //执行动画
        objectAnimator.start();
    }
    //组合动画方法
    public void startAnimatorSet(){
        //1.objectAnimator调用静态方法初始化并设置初值
        ObjectAnimator scaleAnimator=ObjectAnimator.ofFloat(animationBg,"ScaleX",1.0f,2.0f,0.5f,3.0f,2.0f);//位移动画中间的值过渡值
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(animationBg,"ScaleY",1.0f,3.0f,0.5f,2.5f,1.5f);
        ObjectAnimator objectAnimator1=ObjectAnimator.ofInt(animationBg,"TextColor",R.color.colorPrimary);
        ObjectAnimator objectAnimator2=ObjectAnimator.ofInt(animationBg,"background",R.drawable.circle_bule_style);
        ObjectAnimator alphaAnimator=ObjectAnimator.ofFloat(animationBg,"alpha",1.0f,0.5f,0.3f,1.0f,0.3f);//透明度动画
        ObjectAnimator translateAnimator=ObjectAnimator.ofFloat(animationBg,"x",0f,100,200,100,0);//缩放动画
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setupEndValues();
        animatorSet.setDuration(5000);
        animatorSet.play(scaleAnimator)
                .with(alphaAnimator)
                .before(translateAnimator)
        .with(objectAnimator)
        .after(objectAnimator1)
        .after(objectAnimator2);
        animatorSet.start();
    }
    int i=0;
    public void addAnimator(){
        circleOne.setBackgroundResource(R.drawable.circle_yanshi);
        circleTwo.setBackgroundResource(R.drawable.circle_yanshi);
        circleThree.setBackgroundResource(R.drawable.circle_yanshi);
        circleFour.setBackgroundResource(R.drawable.circle_yanshi);
        circleFive.setBackgroundResource(R.drawable.circle_yanshi);
        AnimationDrawable animationDrawable1= (AnimationDrawable) circleOne.getBackground();
        AnimationDrawable animationDrawable2= (AnimationDrawable) circleTwo.getBackground();
        AnimationDrawable animationDrawable3= (AnimationDrawable) circleThree.getBackground();
        AnimationDrawable animationDrawable4= (AnimationDrawable) circleFour.getBackground();
        AnimationDrawable animationDrawable5= (AnimationDrawable) circleFive.getBackground();
        animationDrawable1.start();
        animationDrawable2.start();
        animationDrawable3.start();
        animationDrawable4.start();
        animationDrawable5.start();
        AlphaAnimation aaa=new AlphaAnimation(1.0f,1.0f);
        aaa.setDuration(1000);
        aaa.setRepeatMode(10000);
        aaa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                if(i%2==0){
                    circleOne.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }else{
                    circleOne.setTextColor(getResources().getColor(android.R.color.black));
                }
                i++;
            }
        });

        animatorSet=new AnimatorSet();
        ObjectAnimator animator=ObjectAnimator.ofFloat(colorText,"alpha",1.0f,0.0f);
        animator.setDuration(2000);
        animator.setRepeatMode(Animation.REVERSE);
        animator.setRepeatCount(10000);
        animatorSet.play(animator);
        animatorSet.start();

    }
    Animator.AnimatorListener animatorListener=new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            animatorSet.start();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
}
