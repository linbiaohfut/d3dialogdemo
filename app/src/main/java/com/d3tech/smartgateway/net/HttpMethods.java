/**
 * @(#)HttpMethods 1.0.0 2016-07-07 14:39
 * <p>
 * Copyright 2016 Hangzhou PowerStone Technology CO.,LTD.  All rights reserved.
 * PowerStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.d3tech.smartgateway.net;

import android.util.Log;

import com.d3tech.smartgateway.GatewayApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Description:
 * @author linzhuowei (lin_zhuowei@pstone.cc)
 * @version $Revision:1.0.0, $Date: 2016-07-07 14:39 
 * ${tags} 
 */
public class HttpMethods {

    public static final String HTTP_BASE_URL = "http://120.55.182.40:8080";
    public static final String HTTPS_BASE_URL = "https://120.55.182.40:8443";
    private static final int DEFAULT_TIMEOUT = 13;
    private OkHttpClient mOkHttpClient;
    private Retrofit retrofitHttp;
    private Retrofit retrofitHttps;
    private WebHttpServerApi webHttpServerApi;//http请求

    private WebHttpsServerApi webHttpsServerApi;//https请求
    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        //OKHTTP拦截器，用于配置
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("TAG", "OkHttp---message= " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        try {
            InputStream []certificates = new InputStream[]{GatewayApplication.context.getAssets().open("pstone.cer")};
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor) //日志，可以配置 level 为 BASIC / HEADERS / BODY
                    .retryOnConnectionFailure(true)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .addNetworkInterceptor(loggingInterceptor)
                    .sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null))//证书
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        retrofitHttp = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HTTP_BASE_URL)
                .build();

        webHttpServerApi = retrofitHttp.create(WebHttpServerApi.class);

        retrofitHttps = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HTTPS_BASE_URL)
                .build();

        webHttpsServerApi = retrofitHttps.create(WebHttpsServerApi.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }
    //获取http请求服务
    public static WebHttpServerApi getWebHttpServerApi(){
        return getInstance().webHttpServerApi;
    }
    //获取https请求服务
    public static WebHttpsServerApi getWebHttpsServerApi(){
        return getInstance().webHttpsServerApi;
    }
}
