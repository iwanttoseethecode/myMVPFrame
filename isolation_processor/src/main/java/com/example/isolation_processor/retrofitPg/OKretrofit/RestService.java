package com.example.isolation_processor.retrofitPg.OKretrofit;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
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
 * Created by luoling on 2019/9/20.
 * description:
 */
public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> map);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url,@FieldMap Map<String,Object> params);

    @DELETE
    Call<String> delete(@Url String url,@QueryMap Map<String,Object> params);

    @Streaming
    @GET
    Call<ResponseBody> downLoad(@Url String url,@QueryMap Map<String,Object> params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part List<MultipartBody.Part> partList);

    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    @PUT
    Call<String> putRaw(@Url String url,@Body RequestBody body);

}
