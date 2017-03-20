package com.example.wj.baseproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import dagger.android.support.DaggerFragment;

/**
 * Fragment 基类
 *
 * @author 王杰
 */
public class BaseFragment extends DaggerFragment { // 继承Dagger2 Android support包下的

    /** Context 对象 */
    private BaseActivity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = (BaseActivity) getActivity();

        // 进入界面时打印当前类名，方便调试查错
        Logger.d("Fragment in ---->" + getClass().getSimpleName());
    }
}
