package com.yss.todaynews;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.yss.news.utill.NewsModel;

/**
 * Created by Administrator on 2016/10/26.
 */
public class NewsContentActivity extends Activity {
    TextView contentTime;
    TextView contentTitle;
    ImageView contentImage;
    String newsTitle;
    String newTime;
    int newTitleImage;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        accepterData();
        contentTitle= (TextView) findViewById(R.id.content_title);
        contentTime= (TextView) findViewById(R.id.content_time);
        contentImage= (ImageView) findViewById(R.id.content_img);
        contentTitle.setText(newsTitle);
        contentTime.setText(newTime);
        contentImage.setImageResource(newTitleImage);
    }
    public void accepterData(){
        Intent intent=getIntent();
        if (intent!=null){
            Bundle bundle=intent.getExtras();
            if (bundle!=null){
                NewsModel newsModel= (NewsModel) bundle.getSerializable("newsData");
                if (newsModel!=null){
                     newsTitle=newsModel.getNewstitle();
                     newTime=newsModel.getTime();
                        date=newsModel.getTime();
                    newTitleImage=newsModel.getTitleImage();
                }
            }
        }
    }
}
