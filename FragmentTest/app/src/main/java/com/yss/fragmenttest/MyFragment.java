package com.yss.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2016/10/27.
 */
public class MyFragment extends Fragment {
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState
    ) {
        View view=inflater.inflate(R.layout.fragment_laayout,null);
        button= (Button) view.findViewById(R.id.fanhui);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity= (MainActivity) getActivity();//获得Activity对象
                mainActivity.button.setText("我有回来了！");//改变Activity里面的参数
            }
        });
        return view;
    }
}
