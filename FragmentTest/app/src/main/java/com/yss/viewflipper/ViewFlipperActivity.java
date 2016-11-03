package com.yss.viewflipper;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.yss.fragmenttest.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ViewFlipperActivity extends Activity {
    private ViewFlipper viewFlipper;
    private TextView upBtn;
    private TextView nextBtn;
    private TextView titleTextView;
    private RadioGroup yuanLayout;
    private ArrayList<RadioButton> idlist;
    ImageView imageView;
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
        viewFlipper= (ViewFlipper) findViewById(R.id.viewflipper);
        upBtn= (TextView) findViewById(R.id.up_btn);
        nextBtn= (TextView) findViewById(R.id.next_btn);
        titleTextView= (TextView) findViewById(R.id.title_textview);
        yuanLayout= (RadioGroup) findViewById(R.id.yuan_layout);
        gestureDetector=new GestureDetector(this,onGestureListener);
        upBtn.setOnClickListener(onClickListener);
        nextBtn.setOnClickListener(onClickListener);
        /*Drawable drawable0=getResources().getDrawable(R.drawable.circle_bule_style);
        Drawable drawable1=getResources().getDrawable(R.drawable.circle_style);*/
        Drawable drawable1=getResources().getDrawable(R.drawable.yuan_selector);
        idlist=new ArrayList<RadioButton>();
        for (int i=0;i<imgID.length;i++){
            //添加图片
            imageView=new ImageView(this);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            imageView.setImageResource(imgID[i]);
            viewFlipper.addView(imageView);
            //创建圆点
            RadioButton yuanText=new RadioButton(this);
            RadioGroup.LayoutParams layoutParams=new RadioGroup.LayoutParams(30,30);
            layoutParams.setMargins(5,0,5,0);
            yuanText.setButtonDrawable(null);
            yuanText.setId(i);
            yuanText.setLayoutParams(layoutParams);
            yuanText.setBackgroundResource(R.drawable.yuan_selector);
           // yuanText.setBackground(drawable1);
            idlist.add(yuanText);
            yuanLayout.addView(yuanText);
        }
        viewFlipper.setFlipInterval(3000);//切换时间
        viewFlipper.startFlipping();//开始切换
        viewFlipper.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if(v instanceof FrameLayout){
                    int position=viewFlipper.getDisplayedChild();
                    idlist.get(position).setChecked(true);
                }
            }
        });
       yuanLayout.setOnCheckedChangeListener(onCheckedChangeListener);
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
                viewFlipper.stopFlipping();//先暂停
                viewFlipper.showPrevious();
                viewFlipper.startFlipping();//开始切换
            }else if(x1-x<0){
                viewFlipper.stopFlipping();//先暂停
                viewFlipper.showNext();
                viewFlipper.startFlipping();//开始切换
            }else{
                Toast.makeText(ViewFlipperActivity.this,"请用力滑动！",Toast.LENGTH_SHORT).show();
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
              viewFlipper.stopFlipping();//先暂停
              viewFlipper.showPrevious();//切换上一张
              viewFlipper.startFlipping();//开始切换
              break;
          case R.id.next_btn:
              viewFlipper.stopFlipping();//先暂停
              viewFlipper.showNext();//切换下一张
              viewFlipper.startFlipping();//开始切换
              break;
            case 100:
      }
        }
    };
    RadioGroup.OnCheckedChangeListener onCheckedChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            viewFlipper.setDisplayedChild(checkedId);
        }
    };
}
