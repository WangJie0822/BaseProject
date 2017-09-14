package com.example.wj.baseproject.mvp.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.example.wj.baseproject.R;
import com.example.wj.baseproject.base.BaseActivity;
import com.example.wj.baseproject.mvp.presenter.BlankPresenter;

public class Main2Activity extends BaseActivity<BlankPresenter, ViewDataBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    @Override
    protected void initTitleBar() {

    }
}
