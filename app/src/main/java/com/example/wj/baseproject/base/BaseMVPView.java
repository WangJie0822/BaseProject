package com.example.wj.baseproject.base;

import android.support.annotation.StringRes;

/**
 * MVP View基类
 *
 * @author 王杰
 */
public interface BaseMVPView {

    /**
     * Toast提示
     *
     * @param str 提示文本
     */
    void showToast(String str);

    /**
     * Toast提示
     *
     * @param strResId 提示文本id
     */
    void showToast(@StringRes int strResId);
}
