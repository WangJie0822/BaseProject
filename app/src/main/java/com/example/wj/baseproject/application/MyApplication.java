package com.example.wj.baseproject.application;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.wj.baseproject.dagger.module.MainModule;
import com.example.wj.baseproject.dagger.sub.application.DaggerApplicationSub;
import com.orhanobut.logger.AndroidLogAdapter;
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
public class MyApplication extends Application
        implements HasDispatchingActivityInjector,
        HasDispatchingSupportFragmentInjector {

    /** MyApplication示例对象 */
    private static MyApplication instance;

    /** Dagger2 Activity Injector */
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    /** Dagger2 Fragment Injector */
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // 初始化Logger
        Logger.addLogAdapter(new AndroidLogAdapter());

        DaggerApplicationSub
                .builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(instance);
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
