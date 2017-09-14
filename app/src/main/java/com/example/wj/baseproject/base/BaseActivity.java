package com.example.wj.baseproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.wj.baseproject.R;
import com.example.wj.baseproject.databinding.LayoutBaseBinding;
import com.example.wj.baseproject.databinding.RootHandler;
import com.example.wj.baseproject.util.AppManager;
import com.example.wj.baseproject.util.StatusBarUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Activity基类
 */
public abstract class BaseActivity<P extends BaseMVPPresenter, B extends ViewDataBinding>
        extends AppCompatActivity
        implements BaseMVPView,
        RootHandler.OnTitleClickListener {

    /** 根布局 */
    private View mRootView;

    /** Log标记 */
    protected static String TAG;

    /** Presenter对象 */
    @Inject
    protected P presenter;

    /** DataBinding对象 */
    protected B mBinding;
    /** 根布局DataBinding对象 */
    protected LayoutBaseBinding rootBinding;

    /** Context对象，保存当前Activity */
    protected AppCompatActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        // 添加到应用管理
        AppManager.getInstance().addActivity(this);

        // Log标记初始化为当前类名
        TAG = getClass().getSimpleName();
        // Context初始化为当前Activity对象
        mContext = this;
        // 进入Activity时，打印当前Activity名称
        Logger.d(TAG);

        init();
    }

    @Override
    protected void onDestroy() {

        // 从应用管理移除
        AppManager.getInstance().removeActivity(this);

        // 界面销毁时取消订阅
        if (presenter != null) {
            presenter.dispose();
            presenter.onDetach();
        }
        super.onDestroy();
    }

    /**
     * 初始化界面
     */
    private void init() {
        // 加载界面
        mRootView = View.inflate(mContext, R.layout.layout_base, null);
        rootBinding = DataBindingUtil.bind(mRootView);
        rootBinding.setHandler(new RootHandler(this));
    }

    @Override
    public void setContentView(int layoutResID) {

        initTitleBar();

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                layoutResID, null, false);

        rootBinding.flContent.removeAllViews();
        rootBinding.flContent.addView(mBinding.getRoot());

        super.setContentView(mRootView);

        initStatusBar();
    }

    /**
     * 初始化状态栏
     */
    protected void initStatusBar() {
        setStatusBar(0, R.color.colorTheme);
    }

    /**
     * 社会资状态栏颜色，默认不透明
     *
     * @param colorResId 　颜色资源id
     */
    protected void setStatusBar(@ColorRes int colorResId) {
        setStatusBar(0, colorResId);
    }

    /**
     * 设置状态栏颜色、透明度
     *
     * @param alpha    透明度 0~255
     * @param colorRes 颜色资源id
     */
    protected void setStatusBar(int alpha, @ColorRes int colorRes) {
        if (alpha < 0 || alpha > 255) {
            throw new RuntimeException("The value of the alpha must between 0 and 255");
        }
        // noinspection deprecation
        StatusBarUtil.setColor(this, getResources().getColor(colorRes), alpha);
    }

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
        setTitleText(getString(strResId));
    }

    /**
     * 设置标题文本
     *
     * @param str 文本
     */
    protected void setTitleText(String str) {
        rootBinding.getHandler().setShowTvTitle(true);
        rootBinding.getHandler().setTvTitle(str);
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

    /**
     * 获取右侧文本
     *
     * @return 右侧文本
     */
    protected String getTvRight() {
        return rootBinding.getHandler().getTvRight();
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
                ((AnimationDrawable) drawable).stop();
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
                ((AnimationDrawable) drawable).stop();
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
                ((AnimationDrawable) drawable).stop();
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
                ((AnimationDrawable) drawable).start();
            }
            handler.setShowLoading(true);
        }
    }

    protected void onListComplete() {
    }

    @Override
    public void onLeftClick() {
        finish();
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
