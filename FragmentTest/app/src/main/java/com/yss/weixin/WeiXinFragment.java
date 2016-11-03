package com.yss.weixin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yss.com.yss.utill.Model;
import com.yss.fragmenttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */
public class WeiXinFragment extends android.support.v4.app.Fragment {
    View view1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view1=inflater.inflate(R.layout.weixin_listview,null);
        ListView listView= (ListView)view1.findViewById(R.id.weixin_listview);
        WeiXinAdapter weiXinAdapter=new WeiXinAdapter(getActivity(),getDate(),2,false);
        listView.setAdapter(weiXinAdapter);
        return view1;
    }
    //获得数据源方法
    List<Model> list=new ArrayList<Model>();
    public List<Model> getDate(){
        for(int i=0;i<10;i++){
            Model model=new Model();
            model.setContent("订阅号"+i);
            model.setTime("下午3;10");
            model.setWeixinImg(R.mipmap.ic_launcher);
            list.add(model);
        }
        return list;
    }
}
