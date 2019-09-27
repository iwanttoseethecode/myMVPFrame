package com.example.isolation_processor.retrofitPg;

import com.example.isolation_processor.config.ConfigKeys;
import com.example.isolation_processor.config.Configurator;
import com.example.isolation_processor.retrofitPg.OKretrofit.RestService;
import com.example.isolation_processor.retrofitPg.Rxretrofit.RxRestService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by luoling on 2019/9/20.
 * description:
 */
public class RestCreator {

    /**
     * 可以单独设置的okhttp
     */
    private static final class OkHttpHolder{
        private static final int TIME_OUT=60;
        private static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 产生一个全局的retrofit客户端
     */
    private static final class RetrofitHolder{
        private static final String BASE_URL= Configurator.getInstance().getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    //提供一个接口让调用者得到retrofit对象
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE=RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }

    /**
     * 获取对象
     */
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    //提供接口让调用者得到retrofit对象
    private static final class RxRestServiceHolder{
        private static final RxRestService REST_SERVICE=RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    /**
     * 获取对象
     */
    public static RxRestService getRxRestService(){
        return RxRestServiceHolder.REST_SERVICE;
    }

}
