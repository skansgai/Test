package com.yss.viewflipper;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yss.fragmenttest.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyviewFlipperTest extends Activity {
    private MyViewFlipper mViewFlipper;
    private TextView upBtn;
    private TextView nextBtn;
    private TextView titleTextView;
    private LinearLayout yuanLayout;
    private ArrayList<Integer> idlist;
    GestureDetector gestureDetector;
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
    String[] imgTitle={"你好啊！","吃饭了吗？","打豆豆吗！",
            "来玩啊","干嘛你","德玛西亚","hahaha!","神曲阿忆喔！"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewflipper_test);
        //mViewFlipper= (MyViewFlipper) findViewById(R.id.viewflipper);
        upBtn= (TextView) findViewById(R.id.up_btn);
        nextBtn= (TextView) findViewById(R.id.next_btn);
        titleTextView= (TextView) findViewById(R.id.title_textview);
        yuanLayout= (LinearLayout) findViewById(R.id.yuan_layout);
        gestureDetector=new GestureDetector(this,onGestureListener);
        upBtn.setOnClickListener(onClickListener);
        nextBtn.setOnClickListener(onClickListener);
        idlist=new ArrayList<Integer>();
        for (int i=0;i<imgID.length;i++){
            //添加图片
            ImageView imageView=new ImageView(this);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            imageView.setImageResource(imgID[i]);
            mViewFlipper.addView(imageView);
            //创建圆点
            TextView yuanText=new TextView(this);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(15,15);
            layoutParams.setMargins(5,0,5,0);
            yuanText.setLayoutParams(layoutParams);
            yuanText.setId(100 + i);
            idlist.add(yuanText.getId());
            Drawable drawable=getResources().getDrawable(R.drawable.circle_bule_style);
            yuanText.setBackground(drawable);
            yuanLayout.addView(yuanText);
        }
        mViewFlipper.setFlipInterval(3000);//切换时间
        mViewFlipper.startFlipping();//开始切换
        mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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
            float x=e1.getX();
            float x1=e2.getX();
            if(x1-x>0){
                mViewFlipper.stopFlipping();//先暂停
                mViewFlipper.showPrevious();
                mViewFlipper.startFlipping();//开始切换
            }else if(x1-x<0){
                mViewFlipper.stopFlipping();//先暂停
                mViewFlipper.showNext();
                mViewFlipper.startFlipping();//开始切换
            }else{
                Toast.makeText(MyviewFlipperTest.this,"请用力滑动！",Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);//将触摸时间交付给手势探测器
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.up_btn:
                    mViewFlipper.stopFlipping();//先暂停
                    mViewFlipper.showPrevious();//切换上一张
                    mViewFlipper.startFlipping();//开始切换
                    break;
                case R.id.next_btn:
                    mViewFlipper.stopFlipping();//先暂停
                    mViewFlipper.showNext();//切换下一张
                    mViewFlipper.startFlipping();//开始切换
                    break;
                case 100:
            }
        }
    };
}

