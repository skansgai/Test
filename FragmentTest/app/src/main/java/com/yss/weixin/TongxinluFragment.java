package com.yss.weixin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.*;
import com.yss.fragmenttest.R;

/**
 * Created by Administrator on 2016/10/27.
 */
public class TongxinluFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.baiduzhuce_layout,null);
       /* ListView listView= (ListView)view.findViewById(R.id.weixin_listview);
        List<Model> list=getData();
        WeiXinAdapter weiXinAdapter=new WeiXinAdapter(getActivity(),list,2,false);
        listView.setAdapter(weiXinAdapter);*/
        return view;
    }
   /* List<Model> list=new ArrayList<Model>();
    public List<Model> getData(){
        for (int i=0;i<10;i++){
            Model model=new Model();
            model.setContent("你好！！"+i);
            list.add(model);
        }
        return list;
    }*/
}
