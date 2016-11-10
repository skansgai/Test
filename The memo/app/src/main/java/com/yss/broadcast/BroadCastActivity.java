package com.yss.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.yss.thememo.R;
/**
 * Created by Administrator on 2016/11/10.
 */
public class BroadCastActivity extends Activity {
    TextView registerButton;
    TextView unregisterButton;
    TextView sendButton;
    MyBroadCast myBroadCast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        registerButton= (TextView) findViewById(R.id.register_button);
        unregisterButton= (TextView) findViewById(R.id.unregister_button);
        sendButton= (TextView) findViewById(R.id.send_button);
        myBroadCast=new MyBroadCast();
        registerButton.setOnClickListener(onClickListener);
        unregisterButton.setOnClickListener(onClickListener);
        sendButton.setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.register_button:
                    registerBroadCast();
                    break;
                case R.id.unregister_button:
                    unregisterBroadcast();
                    break;
                case R.id.send_button:
                    sendBroadCast();
                    break;
            }
        }
    };
    public void registerBroadCast(){
        /*注册广播*/
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("START");
        this.registerReceiver(myBroadCast,intentFilter);
        System.out.print("音乐播放");
    }
    /*销毁广播*/
    public void unregisterBroadcast(){
        this.unregisterReceiver(myBroadCast);
    }
    /*发送广播*/
    public void sendBroadCast(){
        Intent intent=new Intent();
        intent.putExtra("ID",110);
        intent.setAction("START");//设置动作
        sendBroadcast(intent);//发送
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
    }
}
