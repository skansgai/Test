package com.yss.todaynews;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Administrator on 2016/10/26.
 * 这个demo是模仿今日头条消息推介弹出推介内容
 */
public class HeadActivity extends Activity {
    ImageView youxiangImg;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_layout);
        youxiangImg= (ImageView) findViewById(R.id.emil_img);
        textView2= (TextView) findViewById(R.id.textView2);
        youxiangImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView2.getVisibility()==View.VISIBLE){
                    createDialog();
                    textView2.setVisibility(View.GONE);
                }
            }
        });
    }
    //创建弹出框的方法
    public void createDialog(){
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=layoutInflater.inflate(R.layout.popupwindow_layout,null);
        TextView dakaiBtn= (TextView) view.findViewById(R.id.dakai_btn);
        ImageView x_img= (ImageView) view.findViewById(R.id.x_img);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        Window window=alertDialog.getWindow();
        dakaiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeadActivity.this,"恭喜您打开成功！",Toast.LENGTH_SHORT).show();
                textView2.setVisibility(View.VISIBLE);
                alertDialog.dismiss();
            }
        });
        x_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeadActivity.this,"取消成功！",Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        //WindowManager wp=window.getAttributes();
        alertDialog.show();
    }
}
