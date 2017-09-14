package com.example.wj.baseproject.dagger.module;

import android.content.Context;

import com.example.wj.baseproject.net.LogInterceptor;
import com.example.wj.baseproject.net.ParametersInterceptor;
import com.example.wj.baseproject.net.RXApi;
import com.example.wj.baseproject.net.UrlDefinition;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 王杰
 */
@Module
public class MainModule {

    private Context mContext;

    public MainModule(Context context) {
        mContext = context;
    }

    @Provides
    Context getContext() {
        return mContext;
    }

    @Provides
    @Singleton
    ParametersInterceptor getParametersInterceptor() {
        return new ParametersInterceptor();
    }

    @Provides
    @Singleton
    LogInterceptor getLogInterceptor() {
        return new LogInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient initOkHttpClient(ParametersInterceptor parametersInterceptor,
                                  LogInterceptor logInterceptor) {
        return new OkHttpClient.Builder()
//                .addInterceptor(parametersInterceptor)  // 设置拦截器，添加公共参数
                .addInterceptor(logInterceptor)         // 设置拦截器，测试环境时打印请求数据
                .build();
    }

    @Provides
    @Singleton
    Retrofit initRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(UrlDefinition.BASE_URL)                            // 设置服务器地址
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // 使用RxJava2
                .addConverterFactory(GsonConverterFactory.create())         // 使用Gson解析
                .client(client)                                             // 使用OkHttp
                .build();
    }

    @Provides
    @Singleton
    RXApi netClient(Retrofit retrofit) {
        return retrofit.create(RXApi.class);
    }
}
