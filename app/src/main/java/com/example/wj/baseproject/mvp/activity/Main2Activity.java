package com.example.wj.baseproject.mvp.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.widget.Toast;

import com.example.wj.baseproject.A;
import com.example.wj.baseproject.R;
import com.example.wj.baseproject.base.BaseActivity;
import com.example.wj.baseproject.mvp.presenter.BlankPresenter;

import javax.inject.Inject;

public class Main2Activity extends BaseActivity<BlankPresenter, ViewDataBinding> {

    @Inject
    A a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toast.makeText(mContext, "a:" + a, Toast.LENGTH_SHORT).show();
    }
}
