package com.example.wj.baseproject.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.wj.baseproject.R;

/**
 * Toast工具类
 */
public class ToastUtil {

    /**
     * Context对象
     */
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    /**
     * 私有化构造
     */
    private ToastUtil() {
    }

    /**
     * 绑定Context对象，建议在Application中绑定
     *
     * @param context Context对象
     */
    public static void bindContext(Context context) {
        mContext = context;
    }

    /**
     * 弹Toast
     *
     * @param str 提示文本
     */
    public static void show(String str) {
        if (mContext == null) {
            throw new RuntimeException("Please bind a Context before show");
        }
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹Toast
     *
     * @param strResId Toast文本资源id
     */
    public static void show(@StringRes int strResId) {
        show(mContext.getString(strResId));
    }

    /**
     * 网络异常提示
     */
    public static void showNetError() {
        show(R.string.net_error);
    }
}
