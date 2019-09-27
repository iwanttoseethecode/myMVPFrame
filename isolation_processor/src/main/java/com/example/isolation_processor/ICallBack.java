package com.example.isolation_processor;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public interface ICallBack {

    void onRequestStart();
    void onRequestEnd();
    void onSuccess(String result);
    void onFailure(String e);
    void onError(int code,String msg);

}
