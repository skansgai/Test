package com.yss.timeviewdemo.utill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yss.timeviewdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ZhihuAdapter extends BaseAdapter {
    Context context ;
    List<ZhiHuNewsModel>list;
    LayoutInflater layoutInflater;
    public ZhihuAdapter(Context context , List<ZhiHuNewsModel>list){
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
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.news_listview_item,null);
        }
        TextView titleText= (TextView) convertView.findViewById(R.id.titleText);
        RelativeLayout newImg= (RelativeLayout) convertView.findViewById(R.id.new_img);
        TextView duoTu= (TextView) convertView.findViewById(R.id.duotu);
        ZhiHuNewsModel zhiHuNewsModel=list.get(position);
        titleText.setText(zhiHuNewsModel.getTitle());
        newImg.setBackgroundResource(zhiHuNewsModel.getImg());
        if(zhiHuNewsModel.isshowImg){
            duoTu.setVisibility(View.VISIBLE);
        }else{
            duoTu.setVisibility(View.GONE);
        }
        return convertView;
    }
}
