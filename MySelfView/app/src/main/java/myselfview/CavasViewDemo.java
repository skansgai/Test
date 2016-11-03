package myselfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.yss.myselfview.R;

/**
 * Created by Administrator on 2016/11/3.
 */
public class CavasViewDemo extends View {
    int mScreenWidth;
    int mScreenHeight;
    double PI=Math.PI;
    float pX;//转动一秒，秒针X的偏移
    float pY;//转动一秒，秒针Y的偏移
    int angle=0;
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
        float circleX=mScreenWidth/2;
        float circleY=mScreenHeight/2;
        float circleR=mScreenWidth/3;
        pX= (float) Math.sin(PI/12)*circleR;
        pY= (float) Math.cos(PI/12)*circleR;
        Paint paint=new Paint();//创建一个画笔对象
        paint.setColor(getResources().getColor(R.color.colorAccent));//设置画笔的颜色
        paint.setStrokeWidth(2);//设置画笔宽度
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(circleX,circleY,circleR,paint);
        Paint linePaint=new Paint();
        paint.setColor(getResources().getColor(android.R.color.holo_green_light));
        paint.setStrokeWidth(2);
        canvas.drawLine(circleX,circleY,circleX,circleY-circleR,linePaint);

        Paint numberPaint=new Paint();
        numberPaint.setColor(getResources().getColor(android.R.color.black));
        numberPaint.setStrokeWidth(10);
        numberPaint.setTextSize(50);
        canvas.drawText("12",circleX,circleY-circleR,numberPaint);
        canvas.drawText("3",circleX+circleR,circleY,numberPaint);
        canvas.drawText("6",circleX,circleY+circleR,numberPaint);
        canvas.drawText("9",circleX-circleR,circleY,numberPaint);
        super.onDraw(canvas);
    }

}
