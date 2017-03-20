package com.example.wj.baseproject.application;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.wj.baseproject.BuildConfig;
import com.example.wj.baseproject.dagger.sub.application.DaggerApplicationSub;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import dagger.android.support.HasDispatchingSupportFragmentInjector;

/**
 * Application 类
 *
 * @author 王杰
 */
public class MyApplication extends Application implements HasDispatchingActivityInjector, HasDispatchingSupportFragmentInjector {

    /** MyApplication示例对象 */
    private MyApplication INSTANCE;
    /** Dagger2 Activity Injector */
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    /** Dagger2 Fragment Injector*/
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        initLogger();

        DaggerApplicationSub.create().inject(INSTANCE);
    }

    /**
     * 初始化Logger
     */
    private void initLogger() {

        Logger.init()
                .methodCount(5) // 显示方法层级数
                .logLevel(BuildConfig.DEBUG ?  // 判断是测试包还是正式包，设置是否打印日志
                        LogLevel.FULL : LogLevel.NONE); // FULL打印、NONE不打印
    }

    /**
     * 获取MyApplication示例对象
     *
     * @return MyApplication实例对象
     */
    public MyApplication INSTANCE() {
        return INSTANCE;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
