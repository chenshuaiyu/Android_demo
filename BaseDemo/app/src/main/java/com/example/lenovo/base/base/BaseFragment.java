package com.example.lenovo.base.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/20 14:24
 */
public abstract class BaseFragment extends Fragment {

    private View mView;

    @LayoutRes
    public abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(getLayoutId(), container,false);
        return mView;
    }

    public View find(@IdRes int id){
        return mView.findViewById(id);
    }

}
