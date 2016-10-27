package com.yss.fragmenttest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button button;
    Button fragmentbtn;
    MyFragment myFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.btn);
        fragmentbtn= (Button) findViewById(R.id.gaibian);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个Fragenmt对象
                myFragment=new MyFragment();
                //获得FragmentManager对象
                FragmentManager fragmentManager=getFragmentManager();
                //获得设置操作对象
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                //添加到视图
                transaction.add(R.id.main_layout,myFragment);
                //显示出
                transaction.commit();
            }
        });
        fragmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFragment.button.setText("我真的回来了！！！");
            }
        });
    }
}
