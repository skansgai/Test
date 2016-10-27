package com.yss.todaynews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yss.news.utill.NewsAdapetr;
import com.yss.news.utill.NewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 * 这个demo是模仿今日头条消息推介弹出推介内容
 */
public class HeadActivity extends Activity {
    //导航栏数据分类数据
    String[] daohangStr={
            "推介","视频","热点","推介","视频",
            "热点","推介", "视频","热点","推介",
            "视频","热点","推介","视频","热点",
            "推介","视频","热点"};
    String[] nesData={
            "妈生日我封5万妹妹封3千，看到妈给我回的礼物，我当场掀桌大骂",
            "京东员工被警察带走，贪污能吞掉一家公司总收入的7%！",
            "被疑逼宫？马英九事后说“应统一” 但话中带刺",
            "妈生日我封5万妹妹封3千，看到妈给我回的礼物，我当场掀桌大骂",
            "京东员工被警察带走，贪污能吞掉一家公司总收入的7%！",
            "被疑逼宫？马英九事后说“应统一” 但话中带刺",
            "妈生日我封5万妹妹封3千，看到妈给我回的礼物，我当场掀桌大骂",
            "京东员工被警察带走，贪污能吞掉一家公司总收入的7%！",
            "被疑逼宫？马英九事后说“应统一” 但话中带刺",
            "妈生日我封5万妹妹封3千，看到妈给我回的礼物，我当场掀桌大骂",
            "京东员工被警察带走，贪污能吞掉一家公司总收入的7%！",
            "被疑逼宫？马英九事后说“应统一” 但话中带刺"};
    //标题图片
    int[] imgId={R.mipmap.img_1,R.mipmap.img_2,R.mipmap.img_3,
            R.mipmap.img_4,R.mipmap.iomg_5,R.mipmap.img_6,
            R.mipmap.img_1,R.mipmap.img_2,R.mipmap.img_3,
            R.mipmap.img_4,R.mipmap.iomg_5,R.mipmap.img_6};
    int[][] imgContent={
            {R.mipmap.img_6,R.mipmap.iomg_5,R.mipmap.img_10},
            {R.mipmap.img_4,R.mipmap.img_10,R.mipmap.img_8},
            {R.mipmap.img_1,R.mipmap.iomg_5,R.mipmap.img_10},
            {R.mipmap.img_9,R.mipmap.img_15,R.mipmap.img_14},
            {R.mipmap.img_14,R.mipmap.img_4,R.mipmap.img_10},
            {R.mipmap.img_6,R.mipmap.img_13,R.mipmap.img_10},
            {R.mipmap.img_6,R.mipmap.iomg_5,R.mipmap.img_10},
            {R.mipmap.img_4,R.mipmap.img_10,R.mipmap.img_8},
            {R.mipmap.img_1,R.mipmap.iomg_5,R.mipmap.img_10},
            {R.mipmap.img_9,R.mipmap.img_15,R.mipmap.img_14},
            {R.mipmap.img_14,R.mipmap.img_4,R.mipmap.img_10},
            {R.mipmap.img_6,R.mipmap.img_13,R.mipmap.img_10}};
    String[] mediaName={
            "新华网","今日头条","搜狐新闻",
            "重庆电视台","CCTV_1","腾讯新闻",
            "新华网","今日头条","搜狐新闻",
            "重庆电视台","CCTV_1","腾讯新闻"};
    ImageView youxiangImg;
    TextView textView2;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_layout);
        youxiangImg= (ImageView) findViewById(R.id.emil_img);
        textView2= (TextView) findViewById(R.id.textView2);
        addRadioGroup();
        youxiangImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView2.getVisibility()==View.VISIBLE){
                    createDialog();
                    textView2.setVisibility(View.GONE);
                }
            }
        });

        //RadioGroup监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

        List<NewsModel> list=getData();
        ListView listView= (ListView) findViewById(R.id.new_listview);
        NewsAdapetr newsAdapetr=new NewsAdapetr(this,list);
        listView.setAdapter(newsAdapetr);
        listView.setOnItemClickListener(onItemClickListener);
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
        alertDialog.show();
    }
    //获取数据源方法
    List<NewsModel> list=new ArrayList<NewsModel>();
    public List<NewsModel> getData(){
        for(int i=0;i<nesData.length;i++){
            NewsModel newsModel=new NewsModel();
            newsModel.setNewstitle(nesData[i]);
            newsModel.setDiscussnumber("1255"+i);
            if (i<2){  //当i<2时显示无图片的
                newsModel.setShowstyle(-1);
            }else if (i>=2&&i<4){
                //此时显示一张图片在右
                newsModel.setShowstyle(0);
            }else{ //此时显示三张图片
                newsModel.setShowstyle(1);
            }
            //设置是否热点
            if (i<2){
                newsModel.setStyle(true);
            }else{
                newsModel.setStyle(false);
            }

            //设置是否为推介
            if (2<i&&i<nesData.length){
                newsModel.setTuijie(true);
            }else{
                newsModel.setTuijie(false);
            }
            //设置新闻单位
            newsModel.setMedianame(mediaName[i]);
            //时间
            newsModel.setTime("2016年08月"+i+"日");
            //设置图片
            newsModel.setTitleImage(imgId[i]);
            //设置内容图片
            newsModel.setImage1(imgContent[i][0]);
            newsModel.setImage2(imgContent[i][1]);
            newsModel.setImage3(imgContent[i][2]);
            list.add(newsModel);
        }
        return list;
    }
    //设置listview item 监听事件
    ListView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(HeadActivity.this,NewsContentActivity.class);
            Bundle bundle=new Bundle();
            NewsModel newsModel=list.get(position);
            bundle.putSerializable("newsData",newsModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    //创建RadioButton方法
    public RadioButton createRadioButton(String str){
        RadioButton radioButton=new RadioButton(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10,0,0,0);
        radioButton.setLayoutParams(params);
        radioButton.setPadding(8,8,8,8);
        radioButton.setText(str);
        radioButton.setTextSize(20);
        radioButton.setTextColor(000000);
      //  Drawable drawable=getResources().getDrawable(R)
        radioButton.setButtonDrawable(null);
        radioButton.setGravity(Gravity.CENTER);
        return radioButton;
    }
    //给RdioGroup添加控件
    public void addRadioGroup(){
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        radioGroup= (RadioGroup) findViewById(R.id.radiogroup);
        for (int i=0;i<daohangStr.length;i++){
            RadioButton radioButton= createRadioButton(daohangStr[i]);
            radioGroup.addView(radioButton);
        }
    }
}
