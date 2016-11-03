package com.yss.weixin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.yss.com.yss.utill.Model;
import com.yss.fragmenttest.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */
class WeiXinAdapter extends BaseAdapter {
    boolean shezhi;
    int style=1;
    Context context;
    List<Model> list;
    LayoutInflater layoutInflater;
    public WeiXinAdapter(Context context, List<Model> list, int style, boolean shezhi ){
        this.context=context;
        this.list=list;
        this.style=style;
        this.shezhi=shezhi;
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
        if (convertView==null){
            switch (style){
                case 1:
                    convertView=layoutInflater.inflate(R.layout.weixin_item_fragment,null);
                    break;
                case 2:
                    convertView=layoutInflater.inflate(R.layout.tongxunlu_item_fragment,null);
                    ImageView imageView= (ImageView) convertView.findViewById(R.id.right_img);
                        if(shezhi){

                            if (imageView.getVisibility()==View.GONE){
                                imageView.setVisibility(View.VISIBLE);
                            }
                    }else {
                            if (imageView.getVisibility()==View.GONE){
                                imageView.setVisibility(View.VISIBLE);
                            }
                        }
                    break;
                default:
                    break;
            }
        }

        return convertView;
    }
}
