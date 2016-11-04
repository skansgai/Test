package com.yss.timeviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yang on 2016/11/3.
 */

public class MyTimeView extends View {
    private Paint circlePaint,dialPaint,numberPaint,numberTimePaint;
    //View的宽高
    private float mWidth,mHeight;
    //园的半径
    private float circleRadius;
    //圆心坐标
    private float circleX,circleY;
    private int second,minute;//分和秒
    private  double hours;//小时
    private float numberTimeWidth;//数字时钟的宽
    private float numberTimeHeight;//数字时钟的宽
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                invalidate();
            }
        }
    };
    public MyTimeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    public MyTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        //刻盘园，小时刻度，时针和分针的画笔
        circlePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(getResources().getColor(android.R.color.black));
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10);

        //分钟刻度的画笔
        dialPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        dialPaint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        dialPaint.setStrokeWidth(5);

        //数字画笔
        numberPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
        numberPaint.setStrokeWidth(5);
        numberPaint.setTextSize(30);

        //数字时钟画笔
        numberTimePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        numberTimePaint.setColor(getResources().getColor(android.R.color.holo_green_dark));
        numberTimePaint.setStrokeWidth(5);
        numberTimePaint.setStyle(Paint.Style.STROKE);
        numberTimePaint.setTextSize(20);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth=getMeasuredWidth();
        mHeight=getMeasuredHeight();
        numberTimeHeight=mHeight/3;
        numberTimeWidth=mWidth*2/3;
        if(mWidth<mHeight){
            //圆半径为view的一半，再减10防止沾边
            circleRadius=mWidth/2-10;
            circleX=mWidth/2;
            circleY=mHeight/2;
        }else{
            circleRadius=mHeight/2-10;
            circleX=mWidth/2;
            circleY=mHeight/2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setTimes();
        drawNumberTime(canvas);
        drawCirclePoint(canvas);
        drawCircle(canvas);
        drawDial(canvas);
        drawPointer(canvas);

    }
    /**画指针
     * X点坐标 cos(弧度)*r
     * Y点坐标 sin(弧度)*r
     * toRadians将角度转成弧度
     * 安卓坐标系与数学坐标系不同的地方是X轴是相反的，所以为了调整方向，需要将角度+270度
     * @param canvas
     */
    private void drawPointer(Canvas canvas) {

        canvas.translate(circleX,circleY);
        float hourX = (float) Math.cos(Math.toRadians(hours*30+270))*circleRadius*0.5f;
        float hourY = (float) Math.sin(Math.toRadians(hours*30+270))*circleRadius*0.5f;
        float minuteX = (float) Math.cos(Math.toRadians(minute*6+270))*circleRadius*0.8f;
        float minuteY = (float) Math.sin(Math.toRadians(minute*6+270))*circleRadius*0.8f;
        float secondX = (float) Math.cos(Math.toRadians(second*6+270))*circleRadius*0.8f;
        float secondY = (float) Math.sin(Math.toRadians(second*6+270))*circleRadius*0.8f;
        canvas.drawLine(0,0,hourX,hourY,circlePaint);
        canvas.drawLine(0,0,minuteX,minuteY,circlePaint);
        canvas.drawLine(0,0,secondX,secondY,dialPaint);
        //一秒重绘一次
        handler.sendEmptyMessageDelayed(0,1000);

    }
//画刻度及时间
    private void drawDial(Canvas canvas) {
        //时钟用长一点的刻度，画笔用画圆的画笔
        Point hourStartPoint = new Point(circleX,circleY-circleRadius);
        Point hourEndPoint = new Point(circleX,circleY-circleRadius+40);
        //分钟的刻度要稍微短一些，画笔用画圆的画笔
        Point startPoint2 = new Point(circleX,circleY-circleRadius);
        Point endPoint2 = new Point(circleX,circleY-circleRadius+10);
        //开始画刻度和数字，总共60个刻度，12个时钟刻度，被5整除画一个时钟刻度，被其余的为分针刻度
        String clockNumber;
        for(int i=0;i<60;i++){
            if(i%5==0){
                if(i==0){
                    clockNumber = "12";
                } else{
                    clockNumber = String.valueOf(i/5);
                }
                //时针刻度
                canvas.drawLine(hourStartPoint.getX(),hourStartPoint.getY(),hourEndPoint.getX(),hourEndPoint.getY(),circlePaint);
                //画数字，需在时针刻度末端加30
                canvas.drawText(clockNumber,circleX-numberPaint.measureText(clockNumber)/2,hourEndPoint.getY()+30,numberPaint);
            } else{
                //画分针刻度
                canvas.drawLine(startPoint2.getX(),startPoint2.getY(),endPoint2.getX(),endPoint2.getY(),circlePaint);
            }
            //画布旋转6度
            canvas.rotate(360/60,circleX,circleY);
        }
    }
    //画圆方法
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(circleX,circleY,circleRadius,circlePaint);
    }

    private void drawCirclePoint(Canvas canvas) {
        canvas.drawCircle(circleX,circleX,5,circlePaint);
    }
    private void drawNumberTime(Canvas canvas){
        String timeSecond=second+10+"";
        String timeMinute=minute+":";
        String timeHours=(int)hours+12+":";
        Paint timePaint = new Paint();
        timePaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        timePaint.setStrokeWidth(5);
        timePaint.setStyle(Paint.Style.STROKE);
        timePaint.setTextSize(50);
        canvas.drawText(timeSecond,numberTimeWidth+100,numberTimeHeight/2,timePaint);
        canvas.drawText(timeMinute,numberTimeWidth,numberTimeHeight/2,timePaint);
        canvas.drawText(timeHours,numberTimeWidth-100,numberTimeHeight/2,timePaint);

    }

    public MyTimeView(Context context) {
        super(context);
    }
    public void startClock(){
        setTimes();
        invalidate();
    }
//设置时间
    private void setTimes() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        second = getTimes(date,Calendar.SECOND);
        minute = getTimes(date,Calendar.MINUTE);
        hours = getTimes(date,Calendar.HOUR)+minute/12*0.2;
    }
//获得时间
    private int getTimes(Date date,int calendarField){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendarField);
    }
    public void stopClock(){
        handler.removeMessages(0);
    }
}
