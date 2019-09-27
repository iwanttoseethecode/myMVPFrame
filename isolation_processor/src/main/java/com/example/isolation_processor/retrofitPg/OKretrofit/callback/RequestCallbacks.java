package com.example.isolation_processor.retrofitPg.OKretrofit.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by luoling on 2019/9/20.
 * description:
 */
public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public RequestCallbacks(IRequest request,ISuccess success,IFailure failure,IError error){
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else{
            if (ERROR != null){
                ERROR.onError(response.code(),response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null){
            FAILURE.onFailure(t.getMessage());
        }
        if (REQUEST != null){
            REQUEST.onRequestEnd();
        }
    }
}
