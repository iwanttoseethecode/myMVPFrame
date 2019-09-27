package com.example.mymvpframe.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.isolation_processor.HttpHelper;
import com.example.isolation_processor.OkHttpProcessor;
import com.example.isolation_processor.RetrofitProcessor;
import com.example.isolation_processor.RxRetrofitProcessor;
import com.example.isolation_processor.VolleyHttpProcessor;
import com.example.isolation_processor.XUtilsProcessor;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public class BaseApplication extends Application {

    private static Handler UIHandler;
    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = getApplicationContext();

        HttpHelper.getInstance().init(getApplicationContext(),"http://101.37.82.26:7003",new RxRetrofitProcessor());
//        HttpHelper.getInstance().init(new RetrofitProcessor());
//        HttpHelper.getInstance().init(new VolleyHttpProcessor(getApplicationContext()));
//        HttpHelper.getInstance().init(new XUtilsProcessor(this));
//        HttpHelper.getInstance().init(new OkHttpProcessor);
    }

    public static Handler getUIHandler(){
        if (UIHandler == null){
            UIHandler = new Handler(Looper.getMainLooper());
        }
        return UIHandler;
    }

    public static Context getContext(){
        return applicationContext;
    }

    public static boolean isMainThread(){
        Looper loop = Looper.myLooper();
        if (loop != null && loop == Looper.getMainLooper()){
            return true;
        }
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }

}
