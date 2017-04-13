package com.example.wj.baseproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.wj.baseproject.R;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Fragment 基类
 *
 * @author 王杰
 */
public abstract class BaseFragment<P extends BaseMVPPresenter, B extends ViewDataBinding> extends DaggerFragment// 继承Dagger2 Android support包下的
        implements BaseMVPView {

    /** Context 对象 */
    protected BaseActivity mContext;

    /** Presenter对象 */
    @Inject
    protected P presenter;

    protected B mBinding;

    protected View mRootView;
    protected Toolbar toolbar;
    protected FrameLayout flContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = (BaseActivity) getActivity();

        // 进入界面时打印当前类名，方便调试查错
        Logger.d("Fragment in ---->" + getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initRootView();
        initToolbar();
        View childView = View.inflate(mContext, layoutResId(), null);
        flContent.removeAllViews();
        flContent.addView(childView);
        mBinding = DataBindingUtil.bind(childView);
        initView(childView);
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Rx生命周期
        if (presenter != null) {
            presenter.onDetach();
            presenter.unSubscribe();
        }
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(@StringRes int strResId) {
        Toast.makeText(mContext, strResId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化根布局
     */
    private void initRootView() {

        mRootView = View.inflate(mContext, R.layout.layout_base, null);
        toolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        flContent = (FrameLayout) mRootView.findViewById(R.id.fl_content);
    }

    /**
     * 初始化Toolbar
     */
    protected void initToolbar() {
    }

    /**
     * 初始化布局id
     *
     * @return 布局id
     */
    @LayoutRes
    protected abstract int layoutResId();

    /**
     * 初始化布局
     *
     * @param childView 布局对象
     */
    protected abstract void initView(View childView);
}
