package com.example.isolation_processor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.isolation_processor.config.Configurator;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public class HttpHelper implements IHttpProcessor {

    static class HttpHelperHolder{
        public static HttpHelper INSTANCE = new HttpHelper();
    }

    private HttpHelper(){}

    public static HttpHelper getInstance(){
        return HttpHelperHolder.INSTANCE;
    }

    private IHttpProcessor mIHttpProcessor;

    public void init(Context context,@NonNull String baseUrl, IHttpProcessor iHttpProcessor){
        mIHttpProcessor = iHttpProcessor;
        if (null == baseUrl){
            throw new RuntimeException("baseUrl is null");
        }
        Configurator.getInstance().setAPI_HOST(baseUrl).setAPPLICATION_CONTEXT(context).configReady();
    }

    @Override
    public void get(String paramsUrl, Map<String, Object> map, ICallBack callBack) {
        if (mIHttpProcessor == null){
            return;
        }
        String finalUrl = appendParams(paramsUrl,map);
        mIHttpProcessor.get(finalUrl,map,callBack);
    }

    @Override
    public void post(String paramsUrl, Map<String, Object> params, ICallBack callBack) {
        if (mIHttpProcessor == null){
            return;
        }
        mIHttpProcessor.post(paramsUrl,params,callBack);
    }

    @Override
    public void put(String paramsUrl, Map<String, Object> map, ICallBack callBack) {
        if (mIHttpProcessor == null){
            return;
        }
        mIHttpProcessor.put(paramsUrl,map,callBack);
    }

    @Override
    public void delete(String paramsUrl, Map<String, Object> map, ICallBack callBack) {
        if (mIHttpProcessor == null){
            return;
        }
        String finalUrl = appendParams(paramsUrl,map);
        mIHttpProcessor.delete(finalUrl,map,callBack);
    }

    @Override
    public void downLoad(String paramsUrl, String dir, String fileName, String extension, Map<String, Object> map, ICallBack callBack) {
        if (mIHttpProcessor == null){
            return;
        }
        String finalUrl = appendParams(paramsUrl,map);
        mIHttpProcessor.downLoad(finalUrl,dir,fileName,extension,map,callBack);
    }

    @Override
    public void upload(String paramsUrl, File file, ICallBack callBack) {
        if (mIHttpProcessor == null){
            return;
        }
        mIHttpProcessor.upload(paramsUrl,file,callBack);
    }

    private String appendParams(String url,Map<String,Object> params){
        if (params == null || params.isEmpty()){
            return url;
        }
        StringBuilder sb = new StringBuilder();
        if (sb.indexOf("?")<0){
            sb.append("?");
        }
        if (!sb.toString().endsWith("?")){
            sb.append("&");
        }

        Set<Map.Entry<String,Object>> entries = params.entrySet();
        Iterator<Map.Entry<String,Object>> it = entries.iterator();
        while (it.hasNext()){
            Map.Entry<String,Object> entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue().toString();
            sb.append(key).append("=").append(value).append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

}
