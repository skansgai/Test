package view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by yang on 2016/11/2.
 */

public class SlidingMenu extends HorizontalScrollView {
    LinearLayout mWapper;
    ViewGroup mMenu;
    ViewGroup mContent;
    int mScreenWidth;
    int mMenuWidth;
    int mMenuRightPadding=100;
    boolean onece=false;
    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context,attrs);
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;
        //把dp转换成px
        mMenuRightPadding=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,
                context.getResources().getDisplayMetrics());
    }
/*
//自定义属性使用了调用该方法
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取属性



        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;
        //把dp转换成px
        mMenuRightPadding=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,
                context.getResources().getDisplayMetrics());
    }
*/

    /*public SlidingMenu(Context context) {
       this(context,null);
    }*/

    //设置子View和自己的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!onece){
            mWapper= (LinearLayout) getChildAt(0);
            mMenu= (ViewGroup) mWapper.getChildAt(0);
            mContent= (ViewGroup) mWapper.getChildAt(1);
            mMenuWidth=mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
            mContent.getLayoutParams().width=mScreenWidth;
            onece=true;
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
//通过设置偏移量设置隐藏
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(mMenuWidth,0);
        }
    }
//触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                //隐藏在左边宽度
                int scrollX=getScrollX();
                if (scrollX>=mMenuWidth/2){
                    this.smoothScrollTo(mMenuWidth,0);
                }else{
                    this.smoothScrollTo(0,0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

}
