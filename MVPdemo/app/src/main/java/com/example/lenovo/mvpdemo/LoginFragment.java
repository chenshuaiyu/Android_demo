package com.example.lenovo.mvpdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.mvpdemo.base.BaseFragment;

import cn.bmob.v3.Bmob;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/20 14:28
 */
public class LoginFragment extends BaseFragment implements LoginContract.View{

    private EditText mAccountEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;

    private LoginContract.Presenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void login() {
        String account=mAccountEditText.getText().toString();
        String password=mPasswordEditText.getText().toString();
        if(TextUtils.isEmpty(account) || TextUtils.isEmpty(password)){
            Toast.makeText(getActivity(), "请完整填写", Toast.LENGTH_SHORT).show();
        }else{
            mPresenter.confirm(account, password);
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bmob.initialize(getActivity(), "dad8a60caff2f145881bde7cd77f47a7");

        mAccountEditText=view.findViewById(R.id.account_edit_text);
        mPasswordEditText=view.findViewById(R.id.password_edit_text);
        mLoginButton=view.findViewById(R.id.login_button);mPresenter=new LoginPresenter(getActivity(),this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

}
