package thememo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.yss.thememo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utill.ThememoAdpater;
import utill.ThememoModel;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ThememoActivity extends Activity {
    private AutoCompleteTextView thememoEdit;
    private Button thememoAddBtn;
    private ListView thememoListview;
    String str;
    ThememoAdpater thememoAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_thememo);
        thememoEdit= (AutoCompleteTextView) findViewById(R.id.search_edit);
        thememoAddBtn= (Button) findViewById(R.id.thememo_add_btn);
        thememoListview= (ListView) findViewById(R.id.thememo_listview);
        checkUserPremission();
        getSharedPrefsData();
        List<ThememoModel> list=createData();
        thememoAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThememoActivity.this,ThememoAddActivity.class);
                startActivity(intent);
            }
        });

        thememoAdpater=new ThememoAdpater(this,list);
        thememoListview.setAdapter(thememoAdpater);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        List<ThememoModel> list=createData();
        thememoAdpater=new ThememoAdpater(this,list);
        thememoListview.setAdapter(thememoAdpater);

    }

    //获取数据源
    List<ThememoModel> list=new ArrayList<ThememoModel>();
    public List<ThememoModel> createData(){
        for (int i=0;i<5;i++){
            String time=getTime();
            ThememoModel thememoModel=new ThememoModel();
            thememoModel.setThememoTitle("你好吗？"+str);
            thememoModel.setThememoContent("confont-国内功能很强大且图标内容很丰富的矢量图标库,提供矢量图标下载、在线存储、格式转换等功能。阿里巴巴体验团队倾力打造,设计和前端开发的便捷工具");
            thememoModel.setThememoTime(time);
            list.add(thememoModel);
        }
        return  list;
    }
    //获取当前时间方法
    public String getTime(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");//设置事件格式
        String time=df.format(new Date());
        return time;
    }
    public void getSharedPrefsData(){
        SharedPreferences sharedPreferences=getSharedPreferences("Thememo", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        str=sharedPreferences.getString("Data","1233545");
        editor.commit();

    }
    //获取动态权限
    public void checkUserPremission(){
        if (Build.VERSION.SDK_INT>23){
            ActivityCompat.requestPermissions(this,//上下文
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1001 );//请求码
        }
    }
    //动态权限回调函数
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
