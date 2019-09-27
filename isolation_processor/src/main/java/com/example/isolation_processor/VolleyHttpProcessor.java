package com.example.isolation_processor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolation_processor.config.ConfigKeys;
import com.example.isolation_processor.config.Configurator;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public class VolleyHttpProcessor implements IHttpProcessor {

    private RequestQueue mRequestQueue;

    public VolleyHttpProcessor(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String paramsUrl, Map<String, Object> map, ICallBack callBack) {

    }

    @Override
    public void post(String paramsUrl, Map<String, Object> map, final ICallBack callBack) {
        String url = StringUtils.buildWholeUrl(Configurator.getInstance().<String>getConfiguration(ConfigKeys.API_HOST),paramsUrl);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailure(error.getMessage());
            }
        });
        mRequestQueue.add(stringRequest);
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
