package com.yss.androidexam.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yss.androidexam.R;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ProgressBarActivity extends Activity {
    ProgressBar progressBar;
    int progress=0;
    TextView startProgress;
    TextView endProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        progressBar= (ProgressBar) findViewById(R.id.progressbar);
        startProgress= (TextView) findViewById(R.id.start_progress);
        endProgress= (TextView) findViewById(R.id.end_progress);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progress<100){
                    try {
                        progress+=1;
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message mgs=new Message();
                    mgs.what=progress;
                    handler.sendMessage(mgs);
                    if (progress>=100){
                        progress=0;
                    }
                }
            }
        }).start();

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressBar.setProgress(msg.what);
            startProgress.setText(msg.what+"%");
        }
    };
}
