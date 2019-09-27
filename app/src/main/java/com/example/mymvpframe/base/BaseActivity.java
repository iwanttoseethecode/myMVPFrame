package com.example.mymvpframe.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mymvpframe.constract.BasePresenter;
import com.example.mymvpframe.constract.BaseView;
import com.example.mymvpframe.constract.presenter.AbstractPresenter;

public abstract class BaseActivity<T extends AbstractPresenter<V>,V extends BaseView> extends AppCompatActivity {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attach((V)this);
        registerDataBus();
    }

    protected abstract T createPresenter();

    protected abstract void registerDataBus();
    protected abstract void unregisterDataBus();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        unregisterDataBus();
    }
}
