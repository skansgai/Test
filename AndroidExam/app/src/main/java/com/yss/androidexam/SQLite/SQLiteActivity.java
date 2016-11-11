package com.yss.androidexam.SQLite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.yss.androidexam.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Created by Administrator on 2016/11/11.
 */
public class SQLiteActivity extends Activity{
    SQLiteDatabase sqLiteDatabase;
    TextView open;
    TextView insert;
    TextView changeVersion;
    TextView select;
    ListView listview;
    Cursor cursor;
    TextView sqlId;
    TextView sqlTopic;
    TextView sqlContent;
    int version=3;
    MySQLiteHelper mySQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open= (TextView) findViewById(R.id.open);
        insert= (TextView) findViewById(R.id.insert);
        changeVersion= (TextView) findViewById(R.id.change_version);
        select= (TextView) findViewById(R.id.select);

        sqlId= (TextView) findViewById(R.id.sql_id);
        sqlTopic= (TextView) findViewById(R.id.sql_topic);
        sqlContent= (TextView) findViewById(R.id.sql_content);

        open.setOnClickListener(onClickListener);
        insert.setOnClickListener(onClickListener);
        changeVersion.setOnClickListener(onClickListener);
        select.setOnClickListener(onClickListener);
    }
    /*打开或则创建表*/
public void openSQLite(int version) {
        mySQLiteHelper = new MySQLiteHelper(
                this,
                "diaryOpenHelper.db",
                null,
                version
        );
       sqLiteDatabase = mySQLiteHelper.getReadableDatabase();
}
    int i=0;
    public void insertData(){
        ContentValues contentValues=new ContentValues();
        contentValues.put("_id",i+1);
        contentValues.put("topic","王老五"+i);
        contentValues.put("content","王老五5555555555"+i);
        sqLiteDatabase.insert("diary",null,contentValues);
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.open:
                openSQLite(version);
                Toast.makeText(SQLiteActivity.this,"打开了"+sqLiteDatabase.toString()+"数据库"+version,Toast.LENGTH_SHORT).show();
                break;
            case R.id.insert:
                while (i<10){
                    insertData();
                    i++;
                }
                Toast.makeText(SQLiteActivity.this,"添加了"+i+"条数据"+version,Toast.LENGTH_SHORT).show();
                break;
            case R.id.change_version:
                mySQLiteHelper.onUpgrade(sqLiteDatabase,version,version+1);
                System.out.print("版本更新了");
                Toast.makeText(SQLiteActivity.this,"版本更新了到"+version,Toast.LENGTH_SHORT).show();
                break;
            case R.id.select:
                List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
                String[] from={"_id","topic","content"};
                int[] to={R.id.sql_id,R.id.sql_topic,R.id.sql_content};
                select();
                while (cursor.moveToNext()){
                    for(int k=0;k<cursor.getCount();k++){
                        HashMap<String,Object> map=new HashMap<String,Object>();
                        map.put("_id",cursor.getString(0));
                        map.put("topic",cursor.getString(1));
                        map.put("content",cursor.getString(2));
                        list.add(map);
                    }
                }
                listview= (ListView) findViewById(R.id.listview);
                SimpleAdapter simpleAdapter=new SimpleAdapter(SQLiteActivity.this,
                       list,
                        R.layout.sqlite_listview_item,
                        from,
                        to);
                listview.setAdapter(simpleAdapter);
                break;
        }
        }
    };
public void select(){
    cursor=sqLiteDatabase.query("diary",null,null,null,null,null,null);
}
}
