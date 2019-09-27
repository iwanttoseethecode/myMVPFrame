package com.example.mymvpframe.simpleDataBus;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by luoling on 2019/9/26.
 * description:
 */
public class SimpleDataBus {

    private Set<Object> subscribers;

    private static volatile SimpleDataBus INSTANCE;

    private SimpleDataBus(){
        subscribers = new CopyOnWriteArraySet<Object>();
    }

    public static SimpleDataBus getInstance(){
        if (INSTANCE == null){
            synchronized (SimpleDataBus.class){
                if (INSTANCE == null){
                    INSTANCE = new SimpleDataBus();
                }
            }
        }
        return INSTANCE;
    }

    public synchronized void register(Object subscriber){
        subscribers.add(subscriber);
    }

    public synchronized void unregister(Object subscriber){
        subscribers.remove(subscriber);
    }

    public void post(Object data){
        for (Object subscriber : subscribers){
            callMethodByAnnotation(subscriber,data);
        }
    }

    private void callMethodByAnnotation(Object subscriber, Object data) {
        if (subscriber == null || data == null){
            return;
        }
        String dataName = data.getClass().getName();

        Method[] methods = subscriber.getClass().getDeclaredMethods();
        for (Method method : methods){
            if (method.getAnnotation(RegisterDataBus.class) != null){
//                RegisterDataBus registerDataBus = method.getAnnotation(RegisterDataBus.class);
                method.setAccessible(true);
                Class<?>[] clazzs = method.getParameterTypes();
                if (clazzs == null || clazzs.length != 1){
                    return;
                }
                Class clazz = clazzs[0];
                if (clazz.getName().equals(dataName)){
                    try {
                        method.invoke(subscriber,new Object[]{data});
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
