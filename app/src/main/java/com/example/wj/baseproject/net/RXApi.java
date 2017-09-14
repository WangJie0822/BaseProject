package com.example.wj.baseproject.net;

import com.example.wj.baseproject.mvp.bean.MoviesListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 网络请求方法体
 */
public interface RXApi {

    /**
     * 获取评价最高电影
     */
    @GET
    Observable<MoviesListBean> getHighestRatedMovies(@Url String url);
}
