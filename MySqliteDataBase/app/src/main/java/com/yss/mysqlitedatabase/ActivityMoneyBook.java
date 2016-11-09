package com.yss.mysqlitedatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;
/**
 * Created by Administrator on 2016/11/9.
 */
public class ActivityMoneyBook extends Activity {
    TextView incomeBtn;
    ViewFlipper moneyViewFlipper;
    LayoutInflater layoutInflater;
    MySqliteHelper mySqliteHelper;
    SQLiteDatabase sqLiteDatabase=null;
    int incomeTypeNameId =1;
    int expendTypeNameId =1;
    ContentValues contentValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_book);
        layoutInflater=LayoutInflater.from(this);
        openSqlite();
        moneyViewFlipper= (ViewFlipper) findViewById(R.id.money_viewflipper);
        View incomeView=layoutInflater.inflate(R.layout.view_my_money_book,null);
        moneyViewFlipper.addView(incomeView);
        incomeBtn= (TextView) incomeView.findViewById(R.id.income_btn);
        incomeBtn.setOnClickListener(onClickListener);

    }
    //打开一个数据库
    public void openSqlite(){
        if (mySqliteHelper==null){
            mySqliteHelper=new MySqliteHelper(
                    this,
                    "MoneyBook.db",
                    null,
                    1);
        }
        sqLiteDatabase=mySqliteHelper.getWritableDatabase();
    }
    public void insertMoney(){

    }
    //增加收入来源类型
    public void addIncome(String incomeName){
        contentValues=new ContentValues();
        contentValues.put("id",incomeTypeNameId++);
        contentValues.put("income_name",incomeName);
        sqLiteDatabase.insert("income_type_name",null,contentValues);
    }
    //增加支出来源类型
    public void addExpend(String expendName){
        contentValues=new ContentValues();
        contentValues.put("id",expendTypeNameId++);
        contentValues.put("expend_name",expendName);
        sqLiteDatabase.insert("expend_type_name",null,contentValues);
    }
    //添加具体收入   这里需要传入的有具体收入的ID（让ID自增,不能为null）
    // 收入类型的ID（通过收入类型表获得ID，不能为null） 收入金额（不能为null）
    // 收入时间（获取系统时间） 地点（可以为null先用假数据） 备注（可以为null）
    public void addIncomeSon(){

    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.income_btn:
                    Intent intent=new Intent(ActivityMoneyBook.this,ActivityIncome.class);
                    startActivity(intent);
                break;
            }
        }
    };
}
