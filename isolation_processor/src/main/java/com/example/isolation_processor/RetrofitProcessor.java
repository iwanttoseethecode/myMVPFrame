package com.example.isolation_processor;

import com.example.isolation_processor.config.ConfigKeys;
import com.example.isolation_processor.config.Configurator;
import com.example.isolation_processor.retrofitPg.OKretrofit.RestClient;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IError;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IFailure;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IRequest;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.ISuccess;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.MultipartBody;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public class RetrofitProcessor implements IHttpProcessor {
    @Override
    public void get(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        RestClient.create().params(param).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                callBack.onSuccess(response);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(String failure) {
                callBack.onFailure(failure);
            }
        }).request(new IRequest() {
            @Override
            public void onRequestStart() {
                callBack.onRequestStart();
            }

            @Override
            public void onRequestEnd() {
                callBack.onRequestEnd();
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                callBack.onError(code,msg);
            }
        }).url(url)
                .build()
                .get();
    }

    @Override
    public void post(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        RestClient.create().params(param).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                callBack.onSuccess(response);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(String failure) {
                callBack.onFailure(failure);
            }
        }).request(new IRequest() {
            @Override
            public void onRequestStart() {
                callBack.onRequestStart();
            }

            @Override
            public void onRequestEnd() {
                callBack.onRequestEnd();
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                callBack.onError(code,msg);
            }
        }).url(url)
                .build()
                .post();
    }

    @Override
    public void put(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        RestClient.create().params(param).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                callBack.onSuccess(response);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(String failure) {
                callBack.onFailure(failure);
            }
        }).request(new IRequest() {
            @Override
            public void onRequestStart() {
                callBack.onRequestStart();
            }

            @Override
            public void onRequestEnd() {
                callBack.onRequestEnd();
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                callBack.onError(code,msg);
            }
        }).url(url)
                .build()
                .put();
    }

    @Override
    public void delete(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        RestClient.create().params(param).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                callBack.onSuccess(response);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(String failure) {
                callBack.onFailure(failure);
            }
        }).request(new IRequest() {
            @Override
            public void onRequestStart() {
                callBack.onRequestStart();
            }

            @Override
            public void onRequestEnd() {
                callBack.onRequestEnd();
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                callBack.onError(code,msg);
            }
        }).url(url)
                .build()
                .delete();
    }

    @Override
    public void downLoad(String paramsUrl,String dir,String fileName,String extension, Map<String, Object> map, final ICallBack callBack) {
        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        RestClient.create().params(param).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                callBack.onSuccess(response);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(String failure) {
                callBack.onFailure(failure);
            }
        }).request(new IRequest() {
            @Override
            public void onRequestStart() {
                callBack.onRequestStart();
            }

            @Override
            public void onRequestEnd() {
                callBack.onRequestEnd();
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                callBack.onError(code,msg);
            }
        }).url(url)
                .dir(dir)
                .extension(extension)
                .filename(fileName)
                .build()
                .downLoad();
    }

    @Override
    public void upload(String paramsUrl, File file, final ICallBack callBack) {

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        RestClient.create().success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                callBack.onSuccess(response);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(String failure) {
                callBack.onFailure(failure);
            }
        }).request(new IRequest() {
            @Override
            public void onRequestStart() {
                callBack.onRequestStart();
            }

            @Override
            public void onRequestEnd() {
                callBack.onRequestEnd();
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                callBack.onError(code,msg);
            }
        })
                .url(url)
                .file(file)
                .build()
                .upload();
    }
}
