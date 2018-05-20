package com.example.lenovo.mvpdemo;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.lenovo.mvpdemo.base.FragmentContainerActivity;

public class LoginActivity extends FragmentContainerActivity {

    @Override
    public Fragment createFragment() {
        return new LoginFragment();
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
