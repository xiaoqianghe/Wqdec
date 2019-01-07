package com.yltx.modulewd.net;

import com.yltx.modulebase.net.HttpLogger;
import com.yltx.modulewd.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/12.
 */

public class RxRetrofit {
    private static RxRetrofit instance;

    //    private String BASE_URL = "http://cgjkh5.ygs001.com/api/";//生产环境
//    private String BASE_URL = "http://192.168.4.60:15555";
    private String BASE_URL = "http://192.168.8.49:2014";


//
//    private String BASE_URL = "http://192.168.1.98:7788";


    private int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;
    private ApiService apiService;

    public static RxRetrofit getInstance() {
        if (instance == null) {
            synchronized (RxRetrofit.class) {
                if (instance == null) {
                    instance = new RxRetrofit();
                }
            }
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public RxRetrofit() {
        create();
    }

    public void create() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient())
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    private OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(DEFAULT_TIMEOUT + 1, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT + 1, TimeUnit.SECONDS);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//        if (BuildConfig.DEBUG) {
        if (true) {
            HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            loggingInterceptor.setLevel(level);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }
}
