package com.example.wj.baseproject.dagger.sub.activity;

import com.example.wj.baseproject.mvp.activity.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * MainActivity Dagger2 组件
 *
 * @author 王杰
 */
@Subcomponent
public interface MainActivitySub extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }
}
