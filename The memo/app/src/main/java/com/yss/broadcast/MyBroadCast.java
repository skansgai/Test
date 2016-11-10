package com.yss.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/11/10.
 */
public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("START")){
            System.out.print("音乐播放");
        }else {
            System.out.print("广播没有进来");
        }
    }
}
