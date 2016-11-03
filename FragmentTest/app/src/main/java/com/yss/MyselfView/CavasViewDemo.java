package com.yss.MyselfView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.yss.fragmenttest.R;

/**
 * Created by Administrator on 2016/11/3.
 */
public class CavasViewDemo extends View {
    int mScreenWidth;
    int mScreenHeight;
    public CavasViewDemo(Context context) {
        super(context);
    }

    public CavasViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;
        mScreenHeight=outMetrics.heightPixels;

    }

    public CavasViewDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CavasViewDemo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }
    //计算控件大小的方法

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //进行布局的方法

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    //绘制图形的方法

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint=new Paint();//创建一个画笔对象
        paint.setColor(getResources().getColor(R.color.colorAccent));//设置画笔的颜色
        paint.setStrokeWidth(10);//设置画笔宽度
        canvas.drawCircle(mScreenWidth/2,mScreenHeight/2,mScreenWidth/3,paint);
        super.onDraw(canvas);
    }
}
