package com.example.mymvpframe.constract;

import java.lang.ref.WeakReference;

/**
 * Created by luoling on 2019/9/16.
 * description:
 */
public interface BasePresenter<T extends BaseView> {

    void attach(T t);

    void detach();

}
