package com.example.lenovo.base.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.example.lenovo.base.R;


/**
 * Coder : chenshuaiyu
 * Time : 2018/5/20 14:50
 */
public abstract class FragmentContainerActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private Fragment mFragment;

    public abstract Fragment createFragment();

    @IdRes
    public abstract int getFragmentContainerId();

    @LayoutRes
    public abstract int getLayoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        mFragmentManager=getSupportFragmentManager();
        mFragment=mFragmentManager.findFragmentById(R.id.fragment_container);
        if(null == mFragment){
            mFragmentManager.beginTransaction()
                    .add(getFragmentContainerId(), createFragment())
                    .commit();
        }

    }
}
