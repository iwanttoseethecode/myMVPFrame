package com.example.isolation_processor;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public abstract class HttpCallBack<Result> implements ICallBack {
    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();

        Class<?> clazz = analysisClassInfo(this);
        Result objResult = (Result) gson.fromJson(result,clazz);

        //把转好的对象交给调用层
        onSuccess(objResult);
    }

    @Override
    public void onFailure(String e) {

    }

    public abstract void onSuccess(Result result);

    private Class<?> analysisClassInfo(Object object){
        Type type = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)type).getActualTypeArguments();
        return (Class<?>)params[0];
    }

}
