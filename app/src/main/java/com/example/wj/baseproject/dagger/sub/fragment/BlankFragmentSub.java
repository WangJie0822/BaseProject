package com.example.wj.baseproject.dagger.sub.fragment;

import com.example.wj.baseproject.fragment.MoviesHighestRatedFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * MoviesHighestRatedFragment Dagger2 组件
 *
 * @author 王杰
 */
@Subcomponent
public interface BlankFragmentSub extends AndroidInjector<MoviesHighestRatedFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MoviesHighestRatedFragment> {
    }
}
