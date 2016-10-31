package utill;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/10/31.
 */
public class MySelfCircleDrawable extends Drawable {
    private int radius;
    private int mwidth;
    private int mHeight;
    private Paint mPaint;
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mwidth/2,mHeight/2,radius,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
