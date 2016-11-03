package com.yss.viewflipper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyViewFlipper extends ViewFlipper {
    private OnDisplayChagnedListener mListener;


    public MyViewFlipper(Context context) {
        super(context);
    }

    public MyViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnDisplayChagnedListener(OnDisplayChagnedListener listener) {
        if (mListener != listener) {
            this.mListener = listener;
        }
    }

    @Override
    public void showNext() {
        super.showNext();
        if (mListener!=null){
            mListener.OnDisplayChildChanging(this, super.getDisplayedChild());
        }
    }

    public void showPrevious(){
    super.showPrevious();
    if(mListener!=null){
        mListener.OnDisplayChildChanging(this, super.getDisplayedChild());
    }
}

    public interface OnDisplayChagnedListener {
        void OnDisplayChildChanging(ViewFlipper view, int index);
    }
}
