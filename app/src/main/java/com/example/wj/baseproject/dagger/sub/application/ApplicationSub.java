package com.example.wj.baseproject.dagger.sub.application;

import com.example.wj.baseproject.application.MyApplication;
import com.example.wj.baseproject.dagger.module.ActivityModule;
import com.example.wj.baseproject.dagger.module.FragmentModule;

import dagger.Component;

/**
 * Application Dagger2 组件
 *
 * @author 王杰
 */
@Component(modules = {ActivityModule.class, FragmentModule.class})
public interface ApplicationSub {

    void inject(MyApplication app);
}
