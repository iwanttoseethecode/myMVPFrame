package com.example.isolation_processor.retrofitPg.Rxretrofit;

import com.example.isolation_processor.HttpMethod;
import com.example.isolation_processor.retrofitPg.RestCreator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by luoling on 2019/9/25.
 * description:
 */
public class RxRestClient {

    private Map<String,Object> PARAMS;
    private String URL;
    private RequestBody BODY;
    private File FILE;

    private String DOWNDIR_DIR;
    private String EXTENSION;
    private String FILENAME;

    public RxRestClient(Map<String,Object> params,String url,RequestBody body,File file,String dir,String extension,String fileName){
        this.PARAMS = params;
        this.URL = url;
        this.BODY = body;
        this.FILE = file;
        this.DOWNDIR_DIR = dir;
        this.EXTENSION = extension;
        this.FILENAME = fileName;
    }

    public static RxRestClientBuilder create(){
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod httpMethod){
        RxRestService rxRestService = RestCreator.getRxRestService();
        Observable<String> observable = null;

        switch (httpMethod){
            case GET:
                observable = rxRestService.get(URL,PARAMS);
                break;
            case POST:
                observable = rxRestService.post(URL,PARAMS);
                break;
            case PUT:
                observable = rxRestService.put(URL,PARAMS);
                break;
            case DELETE:
                observable = rxRestService.delete(URL,PARAMS);
                break;
                default:
                    break;
        }
        return observable;
    }

    public final Observable<String> get(){
        return request(HttpMethod.GET);
    }

    public final Observable<String> post(){
        return request(HttpMethod.POST);
    }

    public final Observable<String> put(){
        return request(HttpMethod.PUT);
    }

    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upLoad(){
        RxRestService restService = RestCreator.getRxRestService();

        final RequestBody requestBody = RequestBody.create(MultipartBody.FORM,FILE);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型

        for(String key : PARAMS.keySet()){
            Object value = PARAMS.get(key);
            builder.addFormDataPart(key, value.toString());
        }
        builder.addFormDataPart("file", FILE.getName(), requestBody);
        List<MultipartBody.Part> parts = builder.build().parts();
        Observable<String> observable = restService.upload(URL,parts);

        return observable;
    }

    public final Observable<ResponseBody> downLoad(){
        return RestCreator.getRxRestService().download(URL,PARAMS);
    }

}
