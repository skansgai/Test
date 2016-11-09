package com.yss.mysqlitedatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/8.
 */
public class MySqliteDataBaseActivity extends Activity {
    TextView openButton,insertButton,updataButton,selecteButton,deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openButton= (TextView) findViewById(R.id.open_btn);
        insertButton= (TextView) findViewById(R.id.add_btn);
        updataButton= (TextView) findViewById(R.id.updata_btn);
        selecteButton= (TextView) findViewById(R.id.selecte_btn);
        deleteButton= (TextView) findViewById(R.id.delete_btn);

        openButton.setOnClickListener(onClickListener);
        insertButton.setOnClickListener(onClickListener);
        updataButton.setOnClickListener(onClickListener);
        selecteButton.setOnClickListener(onClickListener);
        deleteButton.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_btn:
                openSqlite();
                break;
            case R.id.add_btn:
                insert();
                break;
            case R.id.updata_btn:
                updata();
                break;
            case R.id.selecte_btn:
                selecte();
                break;
            case R.id.delete_btn:
                delete();
                break;
        }
        }
    };
    SQLiteDatabase sqLiteDatabase=null;
    //打开一个数据库
    public void openSqlite(){
    MySqliteHelper mySqliteHelper=new MySqliteHelper(
            this,
            "MoneyBook.db",
            null,
            1);
        sqLiteDatabase=mySqliteHelper.getWritableDatabase();
    }
    //插入数据
    int i=10;
    public void insert(){
      //  String insert="insert into book values(2,'LOL之巅峰世界','LOL之王','腾讯出版社','什么鬼')";
        //String  str="insert into book values(3,'LOL之巅峰世界','LOL之王','腾讯出版社','什么鬼'),(4,'LOL之巅峰世界','LOL之王','腾讯出版社','什么鬼'),(5,'LOL之巅峰世界','LOL之王','腾讯出版社','什么鬼')";
        if (sqLiteDatabase!=null){
            //sqLiteDatabase.execSQL(str);
            ContentValues contentValues=new ContentValues();
            contentValues.put("bNo",i++);
            contentValues.put("bName","科技之光");
            contentValues.put("bAuther","体育频道");
            contentValues.put("bPublisher","出版社个");
            contentValues.put("bMout","12030");
            sqLiteDatabase.insert("book",null,contentValues);
        }
        //最后记住，不管用何种方式打开了数据库，获得的SQLite对象不再使用时，
        // 都要调用close()来关闭打开的数据库，否则抛出IllegalStateException异常。
    }
    public void updata(){
        //UPDATA 表名 SET 列名 = 新值 ，列名 = 新值 WHERE 列名 = 某值
        /*"create table book(bNo char(2) not null primary key,bName varchar(50)  not null," +
            "bAuther varchar(30) not null," +
            "bPublisher varchar(30) not null,bMout int not null)"*/
        //String updateStr="update book set bName='科技之光' where id=1";
        ContentValues contentValues=new ContentValues();
        contentValues.put("bName","王者荣耀");
/*        contentValues.put("bAuther","体育频道");
        contentValues.put("bPublisher","出版社个");
        contentValues.put("bMout","230120");*/
        String[] str={"10","bName"};
        sqLiteDatabase.update("book",contentValues,"bNo=? and bName=?",str);
        Toast.makeText(this,"数据更新了",Toast.LENGTH_SHORT).show();
    }
    public void delete(){
        //DELETE FROM 表明 WHERE列名=值
        if (sqLiteDatabase!=null){
            sqLiteDatabase.delete("book","bNo=?",new String[]{"5"});
        }
    }
    public void selecte(){
        if(sqLiteDatabase==null);
            openSqlite();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from book",null);
        int num=cursor.getColumnCount();//获取列数
        if (!cursor.isFirst()){//是否在第一行
            cursor.moveToFirst();//将游标移到第一行
          for (int i=0;i<num;i++){
              String columName=cursor.getColumnName(i);//获得每列的列名
              String value=cursor.getString(i);//获得每列的值
              Toast.makeText(this,"数据更新了"+columName+"------------"+value,Toast.LENGTH_SHORT).show();
          }
        }
        while (cursor.moveToNext()){
            for (int i=0;i<num;i++){
                String columName=cursor.getColumnName(i);//获得每列的列名
                String value=cursor.getString(i);//获得每列的值
                Toast.makeText(this,"数据更新了"+columName+"------------"+value,Toast.LENGTH_SHORT).show();
            }
        }
cursor.close();
    }
}
