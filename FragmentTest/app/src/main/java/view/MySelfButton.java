package view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Administrator on 2016/11/3.
 */
public class MySelfButton extends Button{
    //一个参数构造器
    public MySelfButton(Context context) {
        super(context);
    }
    //两个参数构造器
    public MySelfButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //三个参数构造器，当要使用到自定义的
    public MySelfButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MySelfButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    //事件处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
