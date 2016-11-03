package com.yss.MyselfView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/3.
 */
public class CavasViewDemo extends View {
    public CavasViewDemo(Context context) {
        super(context);
    }

    public CavasViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));//设置画笔的颜色
        paint.setStrokeWidth(20);//设置画笔宽度
        canvas.drawLine(0,0,300,300,paint);//画一条直线
        canvas.drawText("你好吗？",50,50,paint);//画一个字符串（文字）
        Paint paint1=new Paint();
        paint1.setColor(getResources().getColor(android.R.color.holo_red_light));//设置画笔的颜色
        paint1.setStrokeWidth(20);//设置画笔宽度
        paint1.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0,0,300,300,paint);//画一条直线
        canvas.drawText("你好吗？",50,50,paint);//画一个字符串（文字）
        canvas.drawCircle(500,500,200,paint1);
        super.onDraw(canvas);
    }
}
