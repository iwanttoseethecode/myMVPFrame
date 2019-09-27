package com.example.mymvpframe.constract.presenter;

import com.example.mymvpframe.constract.BasePresenter;
import com.example.mymvpframe.constract.BaseView;

import java.lang.ref.WeakReference;

/**
 * Created by luoling on 2019/9/16.
 * description:
 */
public class AbstractPresenter<T extends BaseView> implements BasePresenter<T> {

    protected WeakReference<T> viewWrf;

    @Override
    public void attach(T t) {
        viewWrf = new WeakReference<>(t);
    }

    @Override
    public void detach() {
        if (viewWrf != null){
            viewWrf.clear();
            viewWrf = null;
        }
    }
}
