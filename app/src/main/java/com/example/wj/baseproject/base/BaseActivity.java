package com.example.wj.baseproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.wj.baseproject.R;
import com.example.wj.baseproject.util.StatusBarUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Activity基类
 *
 * @author 王杰
 */
public abstract class BaseActivity<P extends BaseMVPPresenter, B extends ViewDataBinding> extends AppCompatActivity
        implements BaseMVPView {

    /** Context 对象 */
    protected BaseActivity mContext;

    /** Presenter对象 */
    @Inject
    protected P presenter;

    /** DataBinding 对象 */
    protected B mBinding;

    protected View mRootView;

    protected Toolbar toolbar;
    protected ActionBar titleBar;
    protected FrameLayout flContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Dagger2 依赖注入，放在onCreate()前
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        mContext = this;

        // 进入界面时打印当前类名，方便调试查错
        Logger.d("Activity in ---->" + getClass().getSimpleName());

        initRootView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Rx生命周期管理
        if (presenter != null) {
            presenter.onDetach();
            presenter.unSubscribe();
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        View content = View.inflate(mContext, layoutResID, null);
        flContent.removeAllViews();
        flContent.addView(content);

        mBinding = DataBindingUtil.bind(content);

        super.setContentView(mRootView);

        initStatusBar();
        initToolbar();
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
     * 显示标题栏
     */
    protected void showTitle() {
        toolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        titleBar = getSupportActionBar();
    }

    /**
     * 初始化根布局
     */
    protected void initRootView() {

        mRootView = View.inflate(mContext, R.layout.layout_base, null);

        toolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        flContent = (FrameLayout) mRootView.findViewById(R.id.fl_content);
    }

    /**
     * 设置状态栏
     */
    protected void initStatusBar() {
        int mAlpha = 0;
        //noinspection deprecation
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), mAlpha);
    }

    /**
     * 设置标题栏
     */
    protected void initToolbar() {

    }
}
