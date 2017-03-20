package com.example.wj.baseproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import dagger.android.AndroidInjection;

/**
 * Activity基类
 *
 * @author 王杰
 */
public class BaseActivity extends AppCompatActivity {

    /** Context 对象 */
    protected BaseActivity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Dagger2 依赖注入，放在onCreate()前
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        mContext = this;

        // 进入界面时打印当前类名，方便调试查错
        Logger.d("Activity in ---->" + getClass().getSimpleName());
    }
}
