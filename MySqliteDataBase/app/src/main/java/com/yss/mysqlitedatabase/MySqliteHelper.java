package com.yss.mysqlitedatabase;
/*查创建收入总-分类表包含ID 收入类别名：create table income_type (id integer primary key autoincrement,income_name varchar(100))
*创建收入表：包含输入ID，输入类别名，收入金额，输入时间，地点，备注
* CREATE TABLE "income_son" ("id" INTEGER PRIMARY KEY AUTOINCREMENT, "incom_id" INTERGER NOT NULL, "income_type_name" VARCHAR(100) NOT NULL, "income_date" DATE NOT NULL, "income_money" VARCHAR(100), "income_address" VARCHAR(100), "income_remark" VARCHAR(200))
* 总支出类别-分类表包含ID 支出类别名：CREATE TABLE expend_type_name(id integer primary key autoincrement,expend_name)
* 创建收入表：包含输入ID，输入类别名，收入金额，输入时间，地点，备注：
* CREATE TABLE "expend_son" ("id" INTEGER PRIMARY KEY AUTOINCREMENT, "expend_type_name" VARCHAR(100) NOT NULL, "expend_date" DATE NOT NULL, "expend_money" VARCHAR(100), "expend_address" VARCHAR(100), "expend_remark" VARCHAR(200), "expend_id" INTEGER)
* 创建总表：有记账类型，ID，支出/收入：create table moneybook (id integer primary key autoincrement,type_name varchar(100),money varchar(100))
* */
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/8.
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(Context context, //上下文对象
                          String name, //数据库名称
                          SQLiteDatabase.CursorFactory factory,//数据库创建工厂
                          int version) {//版本号
        super(context, name, factory, version);
    }
    public MySqliteHelper(Context context,//上下文对象
                          String name,  //数据库名称
                          SQLiteDatabase.CursorFactory factory,//数据库创建工厂
                          int version,//版本号
                          DatabaseErrorHandler errorHandler) {//数据库异常handler
        super(context, name, factory, version, errorHandler);
    }
    //创建数据库是执行该方法
    String CREATE_SQLITEDATABASE="create table book(bNo char(2) primary key,bName varchar(50)  not null," +
            "bAuther varchar(30) not null," +
            "bPublisher varchar(30) not null,bMout int not null)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table income_type (id integer primary key autoincrement,income_name varchar(100))");
        db.execSQL("CREATE TABLE \"income_son\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"incom_id\" INTERGER NOT NULL, \"income_type_name\" VARCHAR(100) NOT NULL, \"income_date\" DATE NOT NULL, \"income_money\" VARCHAR(100), \"income_address\" VARCHAR(100), \"income_remark\" VARCHAR(200))");
        db.execSQL("CREATE TABLE expend_type_name(id integer primary key autoincrement,expend_name)");
        db.execSQL("CREATE TABLE \"expend_son\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"expend_type_name\" VARCHAR(100) NOT NULL, \"expend_date\" DATE NOT NULL, \"expend_money\" VARCHAR(100), \"expend_address\" VARCHAR(100), \"expend_remark\" VARCHAR(200), \"expend_id\" INTEGER)");
        db.execSQL("create table moneybook (id integer primary key autoincrement,type_name varchar(100),money varchar(100))");
    }
    //版本更新执行该方法
    @Override
    public void onUpgrade(SQLiteDatabase db, //数据库对象
                          int oldVersion,//旧的版本号
                          int newVersion) {//新的版本号
    }
}
