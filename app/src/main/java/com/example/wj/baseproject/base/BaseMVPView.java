package com.example.wj.baseproject.base;

/**
 * MVP View基类
 */
public interface BaseMVPView {

    /**
     * 网络请求结束
     */
    void onNetFinished();

    /**
     * 网络故障
     */
    void onNetError();

    /**
     * 无数据
     */
    void onNoData();

    /**
     * 加载中
     */
    void onLoading();
}
