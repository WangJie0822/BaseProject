package com.example.wj.baseproject.handler;

import com.example.wj.baseproject.mvp.bean.MoviesBean;

/**
 * 电影列表事件处理
 */
public interface MoviesItemHandler {

    void onMoviesItemClick(MoviesBean item);
}
