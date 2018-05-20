package com.example.lenovo.mvpdemo.bean;

import cn.bmob.v3.BmobUser;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/20 15:31
 * 用户类
 */
public class User extends BmobUser {

    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
