package com.example.wj.baseproject.dagger.module;

import android.app.Activity;

import com.example.wj.baseproject.activity.Main2Activity;
import com.example.wj.baseproject.activity.MainActivity;
import com.example.wj.baseproject.dagger.sub.activity.Main2ActivitySub;
import com.example.wj.baseproject.dagger.sub.activity.MainActivitySub;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Activity Dagger2 Module
 *
 * @author 王杰
 */
@Module(subcomponents = {MainActivitySub.class, Main2ActivitySub.class})
public abstract class ActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivity(MainActivitySub.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(Main2Activity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindMain2Activity(Main2ActivitySub.Builder builder);
}
