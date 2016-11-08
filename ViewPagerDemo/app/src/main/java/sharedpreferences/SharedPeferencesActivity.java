package sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.yss.viewpagerdemo.R;

/**
 * Created by Administrator on 2016/11/7.
 */
public class SharedPeferencesActivity extends Activity {
    TextView myName;
    TextView myPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        myName= (TextView) findViewById(R.id.my_name);
        myPassword= (TextView) findViewById(R.id.my_password);
        demoSharedPreferences();
    }
    //sharedpreferences用来存储数据，爆粗在本地
    //在安装目录下创建一个文件夹并存储在data/data/包名/shared_prefs的xxx.xml文件中
    public void demoSharedPreferences(){
        SharedPreferences sharedPreferences=getSharedPreferences("yss", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();//获得编辑者对象
      /*  editor.putString("my_name","yss");
        editor.putLong("my_password",123456789L);*/
        editor.remove("my_password");
        editor.commit();//设置提交
        String my_name=sharedPreferences.getString("my_name","default_name");
        long my_password=sharedPreferences.getLong("my_password",0L);
        myName.setText(my_name);
        myPassword.setText(my_password+"");
    }
}
