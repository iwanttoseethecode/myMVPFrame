package com.example.isolation_processor.retrofitPg.OKretrofit;

import com.example.isolation_processor.HttpMethod;
import com.example.isolation_processor.retrofitPg.DownLoadHandler;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IError;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IFailure;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IRequest;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.ISuccess;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.RequestCallbacks;
import com.example.isolation_processor.retrofitPg.RestCreator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by luoling on 2019/9/20.
 * description:
 */
public class RestClient {

    private final Map<String,Object> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    private final File FILE;
    private final String DOWNDIR_DIR;
    private final String EXTENSION;
    private final String FILENAME;

    public RestClient(Map<String,Object> params,String url,IRequest request,ISuccess success,IFailure failure,IError error,RequestBody body,File file,String downloadDir,String extension,String filename){
        this.PARAMS = params;
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.DOWNDIR_DIR = downloadDir;
        this.EXTENSION = extension;
        this.FILENAME = filename;
    }

    public static RestClientBuilder create(){
        return new RestClientBuilder();
    }

    private Callback<String> getRequestCallBack(){
        return new RequestCallbacks(REQUEST,SUCCESS,FAILURE,ERROR);
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null){
            REQUEST.onRequestStart();
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            default:
                break;
        }

        if (call != null){
            call.enqueue(getRequestCallBack());
        }

        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }

    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void upload(){
        RestService restService = RestCreator.getRestService();

        final RequestBody requestBody = RequestBody.create(MultipartBody.FORM,FILE);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型

        for(String key : PARAMS.keySet()){
            Object value = PARAMS.get(key);
            builder.addFormDataPart(key, value.toString());
        }
        builder.addFormDataPart("file", FILE.getName(), requestBody);
        List<MultipartBody.Part> parts = builder.build().parts();
        Call<String> call = restService.upload(URL,parts);

        if (REQUEST != null){
            REQUEST.onRequestStart();
        }
        if (call != null){
            call.enqueue(getRequestCallBack());
        }

        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
    }

    public final void downLoad(){
        new DownLoadHandler(PARAMS,URL,REQUEST,SUCCESS,FAILURE,ERROR,DOWNDIR_DIR,EXTENSION,FILENAME).handleDownLoad();
    }

}
