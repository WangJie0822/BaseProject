package com.example.wj.baseproject.mvp.view;

import com.example.wj.baseproject.base.BaseMVPView;
import com.example.wj.baseproject.mvp.bean.MoviesListBean;

/**
 * 高评分电影View
 *
 * @author 王杰
 */
public interface MoviesHighestRatedView extends BaseMVPView {

    /**
     * 请求数据成功，更新界面
     *
     * @param data 电影数据
     */
    void notifyData(MoviesListBean data);
}
