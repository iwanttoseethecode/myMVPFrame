package com.example.isolation_processor;

import android.os.Handler;

import com.example.isolation_processor.config.ConfigKeys;
import com.example.isolation_processor.config.Configurator;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public class OkHttpProcessor implements IHttpProcessor {

    OkHttpClient mOkHttpClient;
    Handler handler;

    public OkHttpProcessor(){
        handler = new Handler();
        mOkHttpClient = new OkHttpClient.Builder().connectTimeout(8, TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS).build();
    }

    private RequestBody buildRequestBody(Map<String,Object> params){
        FormBody.Builder builder = new FormBody.Builder();
        if (params == null || params.isEmpty()){
            return builder.build();
        }
        for (Map.Entry<String,Object> entry : params.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue().toString();
            builder.add(key,value);
        }
        return builder.build();
    }

    @Override
    public void get(String paramsUrl, Map<String, Object> map, ICallBack callBack) {

    }

    @Override
    public void post(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        RequestBody RequestBody = buildRequestBody(map);
        final String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);
        final Request request =new Request.Builder().post(RequestBody).url(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String result = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(result);
                        }
                    });
                }
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
