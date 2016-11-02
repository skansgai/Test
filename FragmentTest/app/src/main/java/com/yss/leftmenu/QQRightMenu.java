package com.yss.leftmenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import com.yss.fragmenttest.R;

/**
 * Created by Administrator on 2016/11/2.
 */
public class QQRightMenu extends Activity {
    ViewPager viewPager;
    AnimationSet animationSet;
    TranslateAnimation translateAnimation;
    float distrance;
    View view1;
    View view2;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_home);
        viewPager= (ViewPager) findViewById(R.id.qq_viewflipper);
        view1=getLayoutInflater().inflate(R.layout.qq_home,null);
         view2=getLayoutInflater().inflate(R.layout.qq_right_menu,null);
        viewPager.addView(view1);
        viewPager.addView(view2);
        gestureDetector=new GestureDetector(this,onGestureListener);
    }
    GestureDetector.OnGestureListener onGestureListener=new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float  x0=e1.getX();
            float  x1=e2.getX();
            distrance=x1-x0;
            if(distrance>0){
                viewPager.startAnimation(animationSet);
            }
            return true;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        moveAnimation(distrance);
        return  gestureDetector.onTouchEvent(event);
    }
    public void moveAnimation(float distrance){
        if(animationSet==null&&translateAnimation==null){
            animationSet=new AnimationSet(this,null);
            translateAnimation=new TranslateAnimation(0,distrance,0,0);
        }
        animationSet.addAnimation(translateAnimation);
    }
}
