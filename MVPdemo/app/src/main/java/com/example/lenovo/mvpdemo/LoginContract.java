package com.example.lenovo.mvpdemo;

import com.example.lenovo.mvpdemo.base.BasePresenter;
import com.example.lenovo.mvpdemo.base.BaseView;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/20 14:59
 */
public class LoginContract {


    interface Presenter extends BasePresenter{
        //通过Bmob服务器验证
        void confirm(String account, String password);
    }

    interface View extends BaseView<Presenter>{
        //实现数据获取
        void login();
    }

}
