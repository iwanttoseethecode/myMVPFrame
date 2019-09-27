package com.example.isolation_processor;

import android.app.Application;

import com.example.isolation_processor.config.ConfigKeys;
import com.example.isolation_processor.config.Configurator;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public class XUtilsProcessor implements IHttpProcessor {

    public XUtilsProcessor(Application app){
        x.Ext.init(app);
    }

    @Override
    public void get(String paramsUrl, Map<String, Object> map, ICallBack callBack) {

    }

    @Override
    public void post(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);
        RequestParams params = new RequestParams(url);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailure(ex.getMessage());
            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void put(String paramsUrl, Map<String, Object> map, ICallBack callBack) {

    }

    @Override
    public void delete(String paramsUrl, Map<String, Object> map, ICallBack callBack) {

    }

    @Override
    public void downLoad(String paramsUrl,String dir,String fileName,String extension,Map<String,Object> map,ICallBack callBack) {

    }

    @Override
    public void upload(String paramsUrl, File file, ICallBack callBack) {

    }
}
