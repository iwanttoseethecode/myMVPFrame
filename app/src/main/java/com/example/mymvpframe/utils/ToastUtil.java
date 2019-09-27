package com.example.mymvpframe.utils;

import android.widget.Toast;

import com.example.mymvpframe.base.BaseApplication;

/**
 * Created by Administrator on 2018/5/30 0030.
 */

public class ToastUtil {

    public static int SHORTTIME = 0;
    public static int LONGTIME = 1;

    /**
     * 让task在主线程中执行，更新UI直接调用这个方法
     */
    public static void post(Runnable task) {
        if (BaseApplication.isMainThread()) {
            // 在主线程中执行的
            task.run();
        }else {
            // 在子线程中执行的
            BaseApplication.getUIHandler().post(task);
        }
    }

    public static void toast(final int resId){
        toast(resId,SHORTTIME);
    }

    public static void toast(final String text){
        toast(text,SHORTTIME);
    }

    public static void toast(final int resId, final int time) {
        post(new Runnable() {
            @Override
            public void run() {
                if (time == SHORTTIME){
                    Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getString(resId), Toast.LENGTH_SHORT).show();
                }else if(time == LONGTIME){
                    Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getString(resId), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void toast(final String text, final int time) {
        post(new Runnable() {
            @Override
            public void run() {
                if (time == SHORTTIME){
                    Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_SHORT).show();
                }else if (time == LONGTIME){
                    Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
