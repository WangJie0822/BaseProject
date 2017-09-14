package com.example.wj.baseproject.net;

import android.support.annotation.NonNull;

import com.example.wj.baseproject.BuildConfig;
import com.example.wj.baseproject.util.AppUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求拦截器，添加公共参数
 * <p>
 * <p>versionName  :   版本名</p>
 * <p>platform     :   应用平台</p>
 * <p>imei         :   手机IMEI</p>
 * <p>uid          :   用户id</p>
 */
public class ParametersInterceptor implements Interceptor {

    /**
     * 构造方法
     */
    public ParametersInterceptor() {
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter("version", BuildConfig.VERSION_NAME)              // 版本名
                .addQueryParameter("platform", "android")                            // 应用平台
                .addQueryParameter("imei", AppUtil.getIMEI());                       // 手机IMEI

        // 生成新的请求
        Request request = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

        return chain.proceed(request);
    }

}
