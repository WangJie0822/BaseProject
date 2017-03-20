package com.example.wj.baseproject.rx;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RX网络请求
 */
public class RXClientGenerator {

    /** 网络请求接口 */
    private static volatile RXApi rxApi;
    /** Retrofit对象 */
    private static Retrofit retrofit;

    private RXClientGenerator() {

        // 初始化OkHttp
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new NetInterceptor()) // 设置拦截器，添加公共参数，测试环境时打印请求数据
                .build();
        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(RxUrlDefinition.BASE_URL) // 设置服务器地址
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava
                .addConverterFactory(GsonConverterFactory.create()) // 使用Gson解析
                .client(client) // 使用OkHttp
                .build();
    }

    private static class Helper {
        static final RXClientGenerator INSTANCE = new RXClientGenerator();
    }

    /** 获取网络请求单例对象 */
    public static RXClientGenerator getInstance() {
        return Helper.INSTANCE;
    }

    public synchronized RXApi createClient() {
        if (null == rxApi)
            return rxApi = retrofit.create(RXApi.class);
        else
            return rxApi;
    }

}
