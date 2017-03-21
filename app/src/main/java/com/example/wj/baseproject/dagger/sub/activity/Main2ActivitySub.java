package com.example.wj.baseproject.dagger.sub.activity;

import com.example.wj.baseproject.activity.Main2Activity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * MainActivity Dagger2 组件
 *
 * @author 王杰
 */
@Subcomponent
public interface Main2ActivitySub extends AndroidInjector<Main2Activity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<Main2Activity> {

    }
}
