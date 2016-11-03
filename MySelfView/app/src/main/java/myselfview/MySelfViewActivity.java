package myselfview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yss.myselfview.R;

/**
 * Created by Administrator on 2016/11/3.
 */
public class MySelfViewActivity extends Activity {
    int angle = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself_view);
    }
    public void upDate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        angle+=6;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message mgs=new Message();
                    mgs.arg1=angle;
                    if(angle==360){
                        angle=0;
                    }
                }
            }
        }).start();
    }
    private Handler handler=new Handler();

}