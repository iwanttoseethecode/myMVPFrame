package com.example.isolation_processor;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.isolation_processor.config.ConfigKeys;
import com.example.isolation_processor.config.Configurator;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IRequest;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.ISuccess;
import com.example.isolation_processor.retrofitPg.Rxretrofit.RxRestClient;
import com.example.isolation_processor.retrofitPg.Rxretrofit.ScheduleTransformer;
import com.example.isolation_processor.retrofitPg.SaveFileTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by luoling on 2019/9/25.
 * description:
 */
public class RxRetrofitProcessor implements IHttpProcessor {
    @Override
    public void get(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        Observable<String> observable = RxRestClient.create().setParams(param).setUrl(url).build().get();
        callBack.onRequestStart();
        observable.compose(ScheduleTransformer.io_main()).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                callBack.onRequestStart();
            }

            @Override
            public void onNext(Object o) {
                callBack.onSuccess(o.toString());
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(0,e.getMessage());
            }

            @Override
            public void onComplete() {
                callBack.onRequestEnd();
            }
        });
    }

    @Override
    public void post(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        Observable<String> observable = RxRestClient.create().setParams(param).setUrl(url).build().post();
        callBack.onRequestStart();
        observable.compose(ScheduleTransformer.io_main()).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                callBack.onRequestStart();
            }

            @Override
            public void onNext(Object o) {
                callBack.onSuccess(o.toString());
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(0,e.getMessage());
            }

            @Override
            public void onComplete() {
                callBack.onRequestEnd();
            }
        });
    }

    @Override
    public void put(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        Observable<String> observable = RxRestClient.create().setParams(param).setUrl(url).build().put();
        callBack.onRequestStart();
        observable.compose(ScheduleTransformer.io_main()).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                callBack.onRequestStart();
            }

            @Override
            public void onNext(Object o) {
                callBack.onSuccess(o.toString());
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(0,e.getMessage());
            }

            @Override
            public void onComplete() {
                callBack.onRequestEnd();
            }
        });
    }

    @Override
    public void delete(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        Observable<String> observable = RxRestClient.create().setParams(param).setUrl(url).build().delete();
        callBack.onRequestStart();
        observable.compose(ScheduleTransformer.io_main()).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                callBack.onRequestStart();
            }

            @Override
            public void onNext(Object o) {
                callBack.onSuccess(o.toString());
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(0,e.getMessage());
            }

            @Override
            public void onComplete() {
                callBack.onRequestEnd();
            }
        });
    }

    @Override
    public void downLoad(String paramsUrl, final String dir, final String fileName, final String extension, Map<String, Object> map, final ICallBack callBack) {

        HashMap param=new HashMap();
        param.putAll(map);

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        Observable<ResponseBody> observable = RxRestClient.create().setUrl(url).setParams(map).setDir(dir).setFileName(fileName).setExtension(extension).build().downLoad();

        observable.compose(ScheduleTransformer.io_main()).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                callBack.onRequestStart();
            }

            @Override
            public void onNext(Object o) {
                if (o instanceof ResponseBody){

                    ResponseBody responseBody = (ResponseBody) o;
                    SaveFileTask saveFileTask = new SaveFileTask(new IRequest() {
                        @Override
                        public void onRequestStart() {
                            callBack.onRequestStart();
                        }

                        @Override
                        public void onRequestEnd() {
                            callBack.onRequestEnd();
                        }
                    }, new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            callBack.onSuccess(response);
                        }
                    });

                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,dir,extension,fileName,responseBody);

                }
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(0,e.getMessage());
            }

            @Override
            public void onComplete() {
                callBack.onRequestEnd();
            }
        });
    }

    @Override
    public void upload(String paramsUrl, File file, final ICallBack callBack) {

        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);

        Observable<String> observable = RxRestClient.create().setFile(file).setUrl(url).build().upLoad();

        observable.compose(ScheduleTransformer.io_main()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                callBack.onRequestStart();
            }

            @Override
            public void onNext(String s) {
                callBack.onSuccess(s);
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(0,e.getMessage());
            }

            @Override
            public void onComplete() {
                callBack.onRequestEnd();
            }
        });

    }
}
