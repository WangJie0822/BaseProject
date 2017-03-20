package com.example.wj.baseproject.dagger.module;

import android.support.v4.app.Fragment;

import com.example.wj.baseproject.BlankFragment;
import com.example.wj.baseproject.dagger.sub.fragment.BlankFragmentSub;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Fragment Dagger2 Module
 *
 * @author 王杰
 */
@Module(subcomponents = {BlankFragmentSub.class})
public abstract class FragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(BlankFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindBlankFragment(BlankFragmentSub.Builder builder);
}
