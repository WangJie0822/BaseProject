package com.example.wj.baseproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wj.baseproject.R;
import com.example.wj.baseproject.databinding.LayoutBaseBinding;
import com.example.wj.baseproject.databinding.RootHandler;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Fragment基类
 */
public abstract class BaseFragment<P extends BaseMVPPresenter, B extends ViewDataBinding>
        extends DaggerFragment
        implements BaseMVPView, RootHandler.OnTitleClickListener {

    /** Presenter对象 */
    @Inject
    protected P presenter;

    /** DataBinding对象 */
    protected B mBinding;
    /** 根布局DataBinding对象 */
    protected LayoutBaseBinding rootBinding;

    /** Context对象，保存当前Activity */
    protected AppCompatActivity mContext;
    /** Log标记 */
    protected static String TAG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Log标记初始化为当前类名
        TAG = getClass().getSimpleName();
        // Context初始化为当前Fragment所依附Activity对象
        mContext = (AppCompatActivity) getActivity();
        // 进入Fragment时，打印当前Fragment名称
        Logger.d(TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加载根布局
        View rootView = inflater.inflate(R.layout.layout_base, null);
        rootBinding = DataBindingUtil.bind(rootView);
        rootBinding.setHandler(new RootHandler(this));
        // 初始化标题栏
        initTitleBar();
        // 加载子布局
        View childView = inflater.inflate(bindView(), null);
        mBinding = DataBindingUtil.bind(childView);
        rootBinding.flContent.removeAllViews();
        rootBinding.flContent.addView(childView);
        // 初始化布局
        initView();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.dispose();
            presenter.onDetach();
        }
    }

    /**
     * 绑布局
     *
     * @return 当前界面展示对象
     */
    @LayoutRes
    protected abstract int bindView();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化标题栏
     */
    protected abstract void initTitleBar();

    /**
     * 显示标题栏
     */
    protected void showTitle() {
        rootBinding.getHandler().setShowTitle(true);
    }

    /**
     * 设置左侧图标，默认返回按钮
     */
    protected void setIvLeft() {
        setIvLeft(R.mipmap.arrow_left_white);
    }

    /**
     * 设置左侧图标
     *
     * @param resId 图片资源id
     */
    protected void setIvLeft(@DrawableRes int resId) {
        rootBinding.getHandler().setShowIvLeft(true);
        rootBinding.getHandler().setIvLeftResId(resId);
    }

    /**
     * 设置标题文本
     *
     * @param strResId 文本资源id
     */
    protected void setTitleText(@StringRes int strResId) {
        rootBinding.getHandler().setShowTvTitle(true);
        rootBinding.getHandler().setTvTitle(getString(strResId));
    }

    /**
     * 设置右侧图标
     *
     * @param resId 图片资源id
     */
    protected void setIvRight(@DrawableRes int resId) {
        rootBinding.getHandler().setShowIvRight(true);
        rootBinding.getHandler().setIvRightResId(resId);
    }

    /**
     * 设置右侧文本
     *
     * @param strResId 文本资源id
     */
    protected void setTvRight(@StringRes int strResId) {
        rootBinding.getHandler().setShowTvRight(true);
        rootBinding.getHandler().setTvRight(getString(strResId));
    }

    @Override
    public void onNetFinished() {
        RootHandler handler = rootBinding.getHandler();
        if (handler.isShowNetError()) {
            handler.setShowNetError(false);
        }
        if (handler.isShowNoData()) {
            handler.setShowNoData(false);
        }
        if (handler.isShowLoading()) {
            Drawable drawable = rootBinding.ivLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable)drawable).stop();
            }
            handler.setShowLoading(false);
        }
        onListComplete();
    }

    @Override
    public void onNetError() {
        RootHandler handler = rootBinding.getHandler();
        if (handler.isShowNoData()) {
            handler.setShowNoData(false);
        }
        if (handler.isShowLoading()) {
            Drawable drawable = rootBinding.ivLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable)drawable).stop();
            }
            handler.setShowLoading(false);
        }
        if (!handler.isShowNetError()) {
            handler.setShowNetError(true);
        }
        onListComplete();
    }

    @Override
    public void onNoData() {
        RootHandler handler = rootBinding.getHandler();
        if (handler.isShowNetError()) {
            handler.setShowNetError(false);
        }
        if (handler.isShowLoading()) {
            Drawable drawable = rootBinding.ivLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable)drawable).stop();
            }
            handler.setShowLoading(false);
        }
        if (!handler.isShowNoData()) {
            handler.setShowNoData(true);
        }
        onListComplete();
    }

    @Override
    public void onLoading() {
        RootHandler handler = rootBinding.getHandler();
        if (handler.isShowNetError()) {
            handler.setShowNetError(false);
        }
        if (handler.isShowNoData()) {
            handler.setShowNoData(false);
        }
        if (!handler.isShowLoading()) {
            Drawable drawable = rootBinding.ivLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable)drawable).start();
            }
            handler.setShowLoading(true);
        }
    }

    protected void onListComplete() {
    }

    @Override
    public void onLeftClick() {
    }

    @Override
    public void onRightClick() {
    }

    @Override
    public void onNoDataClick() {
        onLoading();
    }

    @Override
    public void onNetErrorClick() {
        onLoading();
    }
}
