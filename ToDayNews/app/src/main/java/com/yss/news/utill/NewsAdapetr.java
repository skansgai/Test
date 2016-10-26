package com.yss.news.utill;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yss.todaynews.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
public class NewsAdapetr extends BaseAdapter {
    Context context;
    List<NewsModel> list;
    LayoutInflater layoutInflater;
    public  NewsAdapetr(Context context,List<NewsModel> list){
        this.context=context;
        this.list=list;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.new_item_layout,null);
            viewHolder=new ViewHolder();
            viewHolder.newsTitle=(TextView)convertView.findViewById(R.id.news_title);
            viewHolder.mediaName=(TextView)convertView.findViewById(R.id.medianame);
            viewHolder.discussNumber=(TextView)convertView.findViewById(R.id.discuss_number);
            viewHolder.newsTime=(TextView)convertView.findViewById(R.id.news_time);

            viewHolder.image1=(ImageView) convertView.findViewById(R.id.img_1);
            viewHolder.image2=(ImageView) convertView.findViewById(R.id.img_2);
            viewHolder.image3=(ImageView) convertView.findViewById(R.id.img_3);

            viewHolder.imgLayout=(LinearLayout)convertView.findViewById(R.id.img_layout);
            viewHolder.titleImg=(ImageView)convertView.findViewById(R.id.title_img);

            viewHolder.tuijieStyle=(TextView)convertView.findViewById(R.id.tuijie_style);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        //获得新闻模型对象
        NewsModel newsModel=list.get(position);
        //设置新闻标题
        viewHolder.newsTitle.setText(newsModel.getNewstitle());


        //设置推介类型，获得推介类型参数
        int is=newsModel.getShowstyle();
        //获得图片布局对象
        if (is==-1){//没有图片
            if(viewHolder.imgLayout.getVisibility()==View.VISIBLE){//如果图片布局为显示
                    viewHolder.imgLayout.setVisibility(View.GONE);
                if(viewHolder.titleImg.getVisibility()==View.VISIBLE)
                    viewHolder.titleImg.setVisibility(View.GONE);
            }
        }else if (is==0){//一张图片
            if(viewHolder.imgLayout.getVisibility()==View.VISIBLE){
                viewHolder.imgLayout.setVisibility(View.GONE);
                if(viewHolder.titleImg.getVisibility()==View.GONE){
                    viewHolder.titleImg.setVisibility(View.VISIBLE);
                }
            }else{
                if(viewHolder.titleImg.getVisibility()==View.GONE){
                    viewHolder.titleImg.setVisibility(View.VISIBLE);
            }
        }
        }else{
            if(viewHolder.imgLayout.getVisibility()==View.GONE){
                viewHolder.imgLayout.setVisibility(View.VISIBLE);
                if(viewHolder.titleImg.getVisibility()==View.VISIBLE){
                    viewHolder.titleImg.setVisibility(View.GONE);
                }
            }else{
                if(viewHolder.titleImg.getVisibility()==View.VISIBLE){
                    viewHolder.titleImg.setVisibility(View.GONE);
                }
            }
        }
        //判断是否为热点
        boolean isRe=newsModel.isStyle();
        //判断是否为推介
        boolean isTui=newsModel.isTuijie();
        if (isRe&&!isTui){
                if(viewHolder.tuijieStyle.getVisibility()==View.GONE) {
                    viewHolder.tuijieStyle.setVisibility(View.VISIBLE);
                    Drawable drawable=context.getResources().getDrawable(R.drawable.tuijie_style_re);
                    viewHolder.tuijieStyle.setBackground(drawable);
                }else{
                    Drawable drawable=context.getResources().getDrawable(R.drawable.tuijie_style_re);
                    viewHolder.tuijieStyle.setBackground(drawable);
                }
        }else if(!isRe&&isTui){
            if(viewHolder.tuijieStyle.getVisibility()==View.VISIBLE){
                Drawable drawable=context.getResources().getDrawable(R.drawable.tuijie_style_blue);
                viewHolder.tuijieStyle.setBackground(drawable);
                viewHolder.tuijieStyle.setText("推");
                viewHolder.tuijieStyle.setTextColor(context.getResources().getColor(android.R.color.holo_blue_dark));
            }else {
                Drawable drawable=context.getResources().getDrawable(R.drawable.tuijie_style_blue);
                viewHolder.tuijieStyle.setBackground(drawable);
                viewHolder.tuijieStyle.setText("推");
                viewHolder.tuijieStyle.setTextColor(context.getResources().getColor(android.R.color.holo_blue_dark));
            }
        }else {
            if (viewHolder.tuijieStyle.getVisibility()==View.VISIBLE){
                viewHolder.tuijieStyle.setVisibility(View.GONE);
            }
        }

        //设置新闻单位
        viewHolder.mediaName.setText(newsModel.getMedianame());
        //设置评价数量
        viewHolder.discussNumber.setText("评论"+newsModel.getDiscussnumber());
        //设置发布时间
        viewHolder.newsTime.setText(newsModel.getTime());
        //设置图片
        viewHolder.titleImg.setImageResource(newsModel.getTitleImage());
        //设置内容图片
        viewHolder.image1.setImageResource(newsModel.getImage1());
        viewHolder.image2.setImageResource(newsModel.getImage2());
        viewHolder.image3.setImageResource(newsModel.getImage3());
        return convertView;
    }
    class ViewHolder{
        TextView newsTitle;
        TextView mediaName;
        TextView discussNumber;
        TextView newsTime;
        ImageView image1;
        ImageView image2;
        ImageView image3;
        ImageView titleImg;
        TextView tuijieStyle;
        LinearLayout imgLayout;

    }
}
