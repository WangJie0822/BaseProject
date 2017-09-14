package com.example.wj.baseproject.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.DrawableRes;

import com.example.wj.baseproject.BR;

/**
 * 根布局Handler
 */
public class RootHandler extends BaseObservable {

    /** 点击事件监听 */
    private OnTitleClickListener listener;

    /** 标记-是否显示标题栏 */
    private boolean showTitle;

    /** 标记-是否显示左侧图标 */
    private boolean showIvLeft;

    /** 标记-是否显示右侧图标 */
    private boolean showIvRight;

    /** 标记-是否显示标题 */
    private boolean showTvTitle;

    /** 标记-是否显示右侧文字 */
    private boolean showTvRight;

    /** 标记-是否显示网络异常 */
    private boolean showNetError;

    /** 标记-是否显示无数据 */
    private boolean showNoData;

    /** 标记-是否显示加载中 */
    private boolean showLoading;

    /** 左侧按钮图片id */
    private int ivLeftResId;

    /** 右侧按钮图片id */
    private int ivRightResId;

    /** 标题文本 */
    private String tvTitle;

    /** 右侧文本 */
    private String tvRight;

    public RootHandler(OnTitleClickListener listener) {
        this.listener = listener;
    }

    @Bindable
    public boolean isShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
        notifyPropertyChanged(BR.showTitle);
    }

    @Bindable
    public boolean isShowIvLeft() {
        return showIvLeft;
    }

    public void setShowIvLeft(boolean showIvLeft) {
        this.showIvLeft = showIvLeft;
        notifyPropertyChanged(BR.showIvLeft);
    }

    @Bindable
    public boolean isShowIvRight() {
        return showIvRight;
    }

    public void setShowIvRight(boolean showIvRight) {
        this.showIvRight = showIvRight;
        notifyPropertyChanged(BR.showIvRight);
    }

    @Bindable
    public boolean isShowTvTitle() {
        return showTvTitle;
    }

    public void setShowTvTitle(boolean showTvTitle) {
        this.showTvTitle = showTvTitle;
        notifyPropertyChanged(BR.showTvTitle);
    }

    @Bindable
    public boolean isShowTvRight() {
        return showTvRight;
    }

    public void setShowTvRight(boolean showTvRight) {
        this.showTvRight = showTvRight;
        notifyPropertyChanged(BR.showTvRight);
    }

    @Bindable
    public boolean isShowNetError() {
        return showNetError;
    }

    public void setShowNetError(boolean showNetError) {
        this.showNetError = showNetError;
        notifyPropertyChanged(BR.showNetError);
    }

    @Bindable
    public boolean isShowNoData() {
        return showNoData;
    }

    public void setShowNoData(boolean showNoData) {
        this.showNoData = showNoData;
        notifyPropertyChanged(BR.showNoData);
    }

    @Bindable
    public boolean isShowLoading() {
        return showLoading;
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
        notifyPropertyChanged(BR.showLoading);
    }

    @Bindable
    public int getIvLeftResId() {
        return ivLeftResId;
    }

    public void setIvLeftResId(@DrawableRes int ivLeftRedId) {
        this.ivLeftResId = ivLeftRedId;
        notifyPropertyChanged(BR.ivLeftResId);
    }

    @Bindable
    public int getIvRightResId() {
        return ivRightResId;
    }

    public void setIvRightResId(@DrawableRes int ivRightResId) {
        this.ivRightResId = ivRightResId;
        notifyPropertyChanged(BR.ivRightResId);
    }

    @Bindable
    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
        notifyPropertyChanged(BR.tvTitle);
    }

    @Bindable
    public String getTvRight() {
        return tvRight;
    }

    public void setTvRight(String tvRight) {
        this.tvRight = tvRight;
        notifyPropertyChanged(BR.tvRight);
    }

    public void onLeftClick() {
        if (listener == null) {
            return;
        }
        listener.onLeftClick();
    }

    public void onRightClick() {
        if (listener == null) {
            return;
        }
        listener.onRightClick();
    }

    public void onNoDataClick() {
        if (listener == null) {
            return;
        }
        listener.onNoDataClick();
    }

    public void onNetErrorClick() {
        if (listener == null) {
            return;
        }
        listener.onNetErrorClick();
    }

    public interface OnTitleClickListener {

        void onLeftClick();

        void onRightClick();

        void onNoDataClick();

        void onNetErrorClick();
    }
}
