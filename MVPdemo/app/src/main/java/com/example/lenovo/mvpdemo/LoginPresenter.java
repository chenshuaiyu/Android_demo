package com.example.lenovo.mvpdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.mvpdemo.bean.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/20 15:03
 */
public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";

    private Context mContext;
    private LoginContract.View mView;

    public LoginPresenter(Context context, LoginContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mContext=context;
    }

    @Override
    public void confirm(String account, String password) {
        BmobQuery<User> query1=new BmobQuery<>();
        query1.addWhereEqualTo("account", account);
        BmobQuery<User> query2=new BmobQuery<>();
        query2.addWhereEqualTo("password", password);
        List<BmobQuery<User>> list=new ArrayList<>();
        list.add(query1);
        list.add(query2);
        BmobQuery<User> query=new BmobQuery<>();
        query.and(list);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                Log.d("数据", "done: "+list.size());
                if(null == e && list.size() != 0){
                    Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void start() {
        Log.i(TAG, "start: ");
    }

    @Override
    public void destroy() {
        Log.i(TAG, "destroy: ");
    }
}
