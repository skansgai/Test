package com.frain.myapplication.File;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.frain.myapplication.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/8.
 */
public class FileTestActivity extends Activity {
    String str;
    TextView addTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beiwanglu);
        addTextview= (TextView) findViewById(R.id.add_textview);
        outPutStream();
        addTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInPut();
                addTextview.setText(str);
            }
        });


    }
    public void outPutStream(){
        try {
            FileOutputStream outputStream=this.openFileOutput("wanglaowu.txt", Context.MODE_APPEND);
            String str="你米娜睡大觉睡觉奥";
            outputStream.write(str.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openInPut(){
        try {
            FileInputStream fileInputStream=this.openFileInput("wanglaowu.txt");
            byte[] bytes= new byte[1024];
            fileInputStream.read(bytes);
            str=new String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
