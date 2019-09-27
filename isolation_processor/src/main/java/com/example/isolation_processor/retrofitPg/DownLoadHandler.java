package com.example.isolation_processor.retrofitPg;

import android.os.AsyncTask;

import com.example.isolation_processor.retrofitPg.OKretrofit.RestClient;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IError;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IFailure;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.IRequest;
import com.example.isolation_processor.retrofitPg.OKretrofit.callback.ISuccess;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by luoling on 2019/9/24.
 * description:
 */
public class DownLoadHandler {

    private final Map<String,Object> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DWONLOAD_DIR;
    private final String EXTENSION;
    private final String FILENAME;

    public DownLoadHandler(Map<String,Object> params, String url, IRequest request, ISuccess success, IFailure failure, IError error, String download_dir, String extension, String fileName ){
        this.PARAMS = params;
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DWONLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.FILENAME = fileName;
    }

    public final void handleDownLoad(){
        RestCreator.getRestService().downLoad(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    //开始把这次下载的结果保存到文件中,使用线程
                    SaveFileTask saveFileTask = new SaveFileTask(REQUEST,SUCCESS);
                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DWONLOAD_DIR,EXTENSION,FILENAME,response.body());
                    //如果下载成功了，就告诉用户
                    if (saveFileTask.isCancelled()){
                        if (REQUEST!=null){
                            REQUEST.onRequestEnd();
                        }
                    }
                }else{
                    if (ERROR!=null){
                        ERROR.onError(response.code(),response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                FAILURE.onFailure(t.getMessage());
            }
        });
    }

}
