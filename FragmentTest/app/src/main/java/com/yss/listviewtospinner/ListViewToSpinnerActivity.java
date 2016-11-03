package com.yss.listviewtospinner;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import com.yss.fragmenttest.R;

import view.MySelfButton;
import view.MySelfListView;

/**
 * Created by Administrator on 2016/11/3.
 */
public class ListViewToSpinnerActivity extends Activity {
    MySelfButton mySelfButton;
    MySelfListView mySelfListView;
    boolean isShow;
    String[] str={"1","2","1","2","1","2","1"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_to_spinner);
        mySelfListView= (MySelfListView) findViewById(R.id.listview_spinner);
        mySelfButton= (MySelfButton) findViewById(R.id.spinner_btn);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                str);
        mySelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isShow){
                    mySelfListView.setAdapter(arrayAdapter);
                    showListView();
                }else {
                    dismissListView();
                }
            }
        });

    }
    public void showListView(){

        if (mySelfListView.getVisibility()==View.GONE){
            mySelfListView.setVisibility(View.VISIBLE);
        }
        isShow=true;
        ObjectAnimator listViewAnimator=ObjectAnimator.ofFloat(mySelfListView,"scaleX",0f,1f);
        listViewAnimator.setDuration(1000);
        ObjectAnimator listViewShow=ObjectAnimator.ofFloat(mySelfListView,"TranslationX",
                -mySelfListView.getWidth(),0);
        listViewShow.setDuration(1000);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(listViewAnimator).with(listViewShow);
        animatorSet.start();
    }
    public void dismissListView(){
        isShow=false;
        ObjectAnimator listViewAnimator=ObjectAnimator.ofFloat(mySelfListView,"scaleX",1f,0f);
        listViewAnimator.setDuration(1000);
        ObjectAnimator listViewShow=ObjectAnimator.ofFloat(mySelfListView,"TranslationX",
                0,-mySelfListView.getWidth());
        listViewShow.setDuration(1000);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(listViewAnimator).with(listViewShow);
        animatorSet.start();
    }
    //事件分发者
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
    //事件处理者
    @Override
    public boolean onTouchEvent(MotionEvent event) {
if (isShow){
    dismissListView();
}
        return super.onTouchEvent(event);
    }
}
