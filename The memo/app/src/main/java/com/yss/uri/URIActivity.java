package com.yss.uri;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yss.thememo.R;

/**
 * Created by Administrator on 2016/11/10.
 */
public class URIActivity extends Activity{
    TextView phoneNumber;
    TextView phoneName;
    LinearLayout mainLayout;
    String[] str={"打开浏览器","拨打电话","直接拨号","获取联系人信息","插入联系人","更新联系人","删除联系人"};
    Intent intent;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneName= (TextView) findViewById(R.id.phone_name);
        phoneNumber= (TextView) findViewById(R.id.phone_number);
        mainLayout= (LinearLayout) findViewById(R.id.main_layout);
        for (int i=0;i<str.length;i++){
            Button button=createButton();
            button.setText(str[i]);
            button.setId(1000+i);
            button.setOnClickListener(onClickListener);
            mainLayout.addView(button);
        }
    }
    public Button createButton(){
        Button button=new Button(this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(20,0,20,0);
        button.setLayoutParams(layoutParams);
        button.setGravity(Gravity.CENTER);
        return button;
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
        switch (v.getId()){
            case 1000:
                /*跳转到浏览器*/
                uri=Uri.parse("http://www.baidu.com");
                intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case 1001:
                /*拨号页面*/
                uri=Uri.parse("tel:18183102376");
                intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case 1002:
                /*直接拨号*/
                if (Build.VERSION.SDK_INT>23) {
                    ActivityCompat.requestPermissions(URIActivity.this,//上下文
                            new String[]{
                                    Manifest.permission.READ_CONTACTS,
                                    Manifest.permission.CALL_PHONE,
                                    Manifest.permission.WRITE_CALENDAR
                            },
                            1001);
                }
                uri=Uri.parse("tel:18183102376");
                intent=new Intent(Intent.ACTION_CALL,uri);
                startActivity(intent);
                break;
            case 1003:
                /*获得联系人*/
                getPhone(URIActivity.this);
                Toast.makeText(URIActivity.this,"获得联系人成功",Toast.LENGTH_SHORT).show();
                break;
            case 1004:
                /*插入联系人*/
                addContact(URIActivity.this,"王老五","18183102323");
                break;
            case 1005:
                /*更新联系人*/
                updataContact(URIActivity.this,ContactsContract.Data.RAW_CONTACT_ID);
                break;
            case 1006:
                /*删除数据*/
                deleteContact(URIActivity.this,ContactsContract.Data.RAW_CONTACT_ID);
                break;
            }
        }
    };
    public void getPhone(Context context){

        ContentResolver contentResolver=context.getContentResolver();//系统自带数据解析器
        //查询conteacts表的所有记录
        Cursor cur=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        //判断记录是否为空
        if (cur.getCount()>0){//获得列数
            //游标初始位置默认为第一条记录上方是否存在，如果存在，指向下一条记录，否则，返回false
            while(cur.moveToNext()){//指向下一条记录，否则，返回false
                String rawContactsId="";
                int coulmnNum=cur.getColumnIndex(ContactsContract.Contacts._ID);//获得ID的列数
                String id=cur.getString(coulmnNum);//根据列数的ID的到对应的值
                //读取rawContactsId
                Cursor rawContactsIdCur=contentResolver.query(ContactsContract.RawContacts.CONTENT_URI,
                        null,
                        ContactsContract.RawContacts.CONTACT_ID+"=?",new String[]{id},null);
                //该查询结果一般只返回一条记录，所以我们直接让游标指向第一条数据
                if(rawContactsIdCur.moveToFirst()){
                    rawContactsId=rawContactsIdCur.getString(rawContactsIdCur.getColumnIndex(
                            ContactsContract.RawContacts._ID
                    ));
                }
                rawContactsIdCur.close();
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))>0){
                    //HAS_PHONE_NUMBER是系统用来存放是否有号码的标示，如果大于0则有
                    //根据查询RAW_CONTACT_ID查询联系人号码
                    Cursor PhoneCur=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID+"=?",
                            new String[]{rawContactsId},null);
                            while (PhoneCur.moveToNext()){
                                String number=
                                        PhoneCur.getString(PhoneCur.getColumnIndex(
                                                ContactsContract.CommonDataKinds.Phone.NUMBER
                                        ));
                                phoneNumber.setText(number);
                                //获得号码名字
                                String name=
                                        PhoneCur.getString(PhoneCur.getColumnIndex(
                                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                                phoneName.setText(name);
                            }
                }
            }
        }
    }
    //获取动态权限
    public void checkUserPremission(){
        if (Build.VERSION.SDK_INT>23){
            ActivityCompat.requestPermissions(this,//上下文
                    new String[]{
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.WRITE_CALENDAR
                    },
                    1001 );//请求码
            Toast.makeText(URIActivity.this,"获得权限",Toast.LENGTH_SHORT).show();
        }
    }
    //动态权限回调函数
    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode ==1001){
        }
        Toast.makeText(URIActivity.this,"权限回调",Toast.LENGTH_SHORT).show();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    //插入联系人
    public void addContact(Context context,String phoneName,String phoneNumber){
        ContentValues values=new ContentValues();
        //先插入一条空数据raw_contacts中，生成rawContactID
        uri= context.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI,values);
        long rawContactID= ContentUris.parseId(uri);
        //向data表中插入数据
        if(phoneName!=""){
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID,rawContactID);//插入RawContactId到第三张表
            values.put(ContactsContract.Contacts.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);//联系人内容类型
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,phoneName);//插入我们用户的名字
            context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
        }
        //向data表中插入数据
        if(phoneNumber!=""){
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID,rawContactID);//插入RawContactId到第三张表
            values.put(ContactsContract.Contacts.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);//联系人内容类型
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,phoneNumber);//插入我们用户的名字
            context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
        }
    }
    //更新联系人
    public void updataContact(Context context, String rawContactId){
        ContentValues values=new ContentValues();
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,"123568566655");//添加手机号码
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);//添加数据类型为手机号
        String where=ContactsContract.Data.RAW_CONTACT_ID+"=? AND"+ContactsContract.Data.METADATA_DIRTY+"=?";//条件语句
        String[] selectionArgs=new String[]{String.valueOf(rawContactId),ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE};
        context.getContentResolver().update(ContactsContract.Data.CONTENT_URI,values,where,selectionArgs);//更新数据内容

    }
    //删除联系人
    public void deleteContact(Context context, String rawContactId){
        String where=ContactsContract.Data.RAW_CONTACT_ID+"=?";
        context.getContentResolver().delete(
                ContactsContract.Data.CONTENT_URI,where,new String[]{rawContactId});
    }
}
