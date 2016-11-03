package view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/11/3.
 */
public class MySelfLinerLayout extends LinearLayout {
    //一个参数构造器
    public MySelfLinerLayout(Context context) {
        super(context);
    }
//两个参数
    public MySelfLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//三个参数当用到自定义属性的时候调用该构造器
    public MySelfLinerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MySelfLinerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    //重写事件分发者

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }
    //重写事件拦截器

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {

        return super.onInterceptHoverEvent(event);
    }
//事件处理器
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
