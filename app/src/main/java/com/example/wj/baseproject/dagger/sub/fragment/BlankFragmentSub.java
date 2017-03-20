package com.example.wj.baseproject.dagger.sub.fragment;

import com.example.wj.baseproject.BlankFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * BlankFragment Dagger2 组件
 *
 * @author 王杰
 */
@Subcomponent
public interface BlankFragmentSub extends AndroidInjector<BlankFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BlankFragment> {
    }
}
