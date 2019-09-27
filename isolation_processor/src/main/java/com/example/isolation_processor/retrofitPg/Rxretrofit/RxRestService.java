package com.example.isolation_processor.retrofitPg.Rxretrofit;

import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by luoling on 2019/9/25.
 * description:
 */
public interface RxRestService {

    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @DELETE
    Observable<String> delete(@Url String url,@QueryMap Map<String,Object> params);

    //下载是直接到内存,所以需要 @Streaming
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url,@QueryMap Map<String,Object> params);

    //上传
    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part List<MultipartBody.Part> partList);

    //原始数据
    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);

    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);

}
