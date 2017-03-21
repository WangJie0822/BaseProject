package com.example.wj.baseproject;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author 王杰
 */
@Module
public class BaseModule {

    @Singleton
    @Provides
    A get() {
        return new A();
    }
}
