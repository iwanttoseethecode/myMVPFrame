package com.example.isolation_processor;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

/**
 * Created by luoling on 2019/9/17.
 * description:
 */
public interface IHttpProcessor {

    /**
     * @param paramsUrl
     * @param map
     * @param callBack 推荐使用HttpCallBack
     */
    void get(String paramsUrl, Map<String,Object> map,ICallBack callBack);

    /**
     * @param paramsUrl
     * @param map
     * @param callBack 推荐使用HttpCallBack
     */
    void post(String paramsUrl, Map<String,Object> map,ICallBack callBack);

    /**
     * @param paramsUrl
     * @param map
     * @param callBack 推荐使用HttpCallBack
     */
    void put(String paramsUrl, Map<String,Object> map,ICallBack callBack);

    /**
     * @param paramsUrl
     * @param map
     * @param callBack 推荐使用HttpCallBack
     */
    void delete(String paramsUrl, Map<String,Object> map,ICallBack callBack);

    /**
     * @param paramsUrl
     * @param dir
     * @param fileName
     * @param extension
     * @param map
     * @param callBack 不推荐使用HttpCallBack
     */
    void downLoad(String paramsUrl,String dir,String fileName,String extension,Map<String,Object> map,ICallBack callBack);

    /**
     * @param paramsUrl
     * @param file
     * @param callBack 推荐使用HttpCallBack
     */
    void upload(String paramsUrl, File file, ICallBack callBack);



}
