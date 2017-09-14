package com.example.wj.baseproject.mvp.module;

import com.example.wj.baseproject.base.BaseMVPModule;
import com.example.wj.baseproject.mvp.bean.MoviesListBean;
import com.example.wj.baseproject.net.UrlDefinition;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 电影数据Module
 *
 * @author 王杰
 */
public class MoviesModule extends BaseMVPModule {

    @Inject
    MoviesModule() {

    }

    /**
     * 获取评价最高电影
     */
    public Disposable getHighestRatedMovies(Consumer<MoviesListBean> success, Consumer<Throwable> throwable) {
        return netClient
                .getHighestRatedMovies(UrlDefinition.GET_HIGHEST_RATED_MOVIES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, throwable);
    }
}
