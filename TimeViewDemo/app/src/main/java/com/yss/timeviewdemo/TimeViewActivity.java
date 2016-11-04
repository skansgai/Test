package com.yss.timeviewdemo;

import android.app.Activity;
import android.os.Bundle;

import com.yss.timeviewdemo.view.MyTimeView;

/**
 * Created by yang on 2016/11/3.
 */

public class TimeViewActivity extends Activity {
    private MyTimeView myTimeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_layout);
        myTimeView= (MyTimeView) findViewById(R.id.timeview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myTimeView.startClock();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myTimeView.stopClock();
    }
}
