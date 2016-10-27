package com.yss.com.yss.utill;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yss.fragmenttest.R;

/**
 * Created by Administrator on 2016/10/27.
 */
public class FaxianFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.web_layout,null);
    }
}
