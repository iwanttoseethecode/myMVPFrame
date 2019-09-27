package com.example.mymvpframe.model;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.example.isolation_processor.HttpCallBack;
import com.example.isolation_processor.HttpHelper;
import com.example.mymvpframe.R;
import com.example.mymvpframe.bean.GirlBean;
import com.example.mymvpframe.bean.LoginUserInfo;
import com.example.mymvpframe.constract.model.IgirlModel;
import com.example.mymvpframe.simpleDataBus.SimpleDataBus;
import com.example.mymvpframe.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoling on 2019/9/16.
 * description:
 */
public class GirlModel implements IgirlModel {

    private String TAG = GirlModel.class.getSimpleName();

    private OnLoadListener mOnLoadListener;

    @Override
    public void loadGirlData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<GirlBean> list = new ArrayList<GirlBean>();
                list.add(new GirlBean(R.drawable.f1, "一星", "****"));
                list.add(new GirlBean(R.drawable.f2, "一星", "****"));
                list.add(new GirlBean(R.drawable.f3, "一星", "****"));
                list.add(new GirlBean(R.drawable.f4, "一星", "****"));
                list.add(new GirlBean(R.drawable.f5, "一星", "****"));
                list.add(new GirlBean(R.drawable.f6, "一星", "****"));
                list.add(new GirlBean(R.drawable.f7, "一星", "****"));
                list.add(new GirlBean(R.drawable.f8, "一星", "****"));
                list.add(new GirlBean(R.drawable.f9, "一星", "****"));
                list.add(new GirlBean(R.drawable.f10, "一星", "****"));

                SimpleDataBus.getInstance().post(list);
            }
        }).start();
    }

    @Override
    public void loadGirlData(OnLoadListener onLoadListener) {
        mOnLoadListener = onLoadListener;

        new Thread(runnable).start();

    }

    @Override
    public void useHttpFrame() {
        ArrayMap<String, Object> param = new ArrayMap<>();
        param.put("in[phone]", "18011389108");
        param.put("in[password]", "hj123456789");
        HttpHelper.getInstance().post("/appcustserver/api/register/addUser/v1",param,new HttpCallBack<LoginUserInfo>(){
            @Override
            public void onRequestStart() {

            }

            @Override
            public void onRequestEnd() {

            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void onSuccess(LoginUserInfo loginUserInfo) {
                Log.d(TAG, "onSuccess: "+loginUserInfo.getOut().getAccessToken()+"  "+loginUserInfo.getOut().getClientKey());
                ToastUtil.toast("onSuccess: "+loginUserInfo.getOut().getAccessToken()+"  "+loginUserInfo.getOut().getClientKey());
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<GirlBean> list = new ArrayList<>();
            list.add(new GirlBean(R.drawable.f1, "一星", "****"));
            list.add(new GirlBean(R.drawable.f2, "一星", "****"));
            list.add(new GirlBean(R.drawable.f3, "一星", "****"));
            list.add(new GirlBean(R.drawable.f4, "一星", "****"));
            list.add(new GirlBean(R.drawable.f5, "一星", "****"));
            list.add(new GirlBean(R.drawable.f6, "一星", "****"));
            list.add(new GirlBean(R.drawable.f7, "一星", "****"));
            list.add(new GirlBean(R.drawable.f8, "一星", "****"));
            list.add(new GirlBean(R.drawable.f9, "一星", "****"));
            list.add(new GirlBean(R.drawable.f10, "一星", "****"));

            mOnLoadListener.onComplete(list);
        }
    };

}
