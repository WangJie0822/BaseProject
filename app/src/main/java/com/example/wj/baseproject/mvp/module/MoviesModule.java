package com.example.wj.baseproject.mvp.module;

import com.example.wj.baseproject.mvp.bean.MoviesListBean;
import com.example.wj.baseproject.rx.RXClientGenerator;
import com.example.wj.baseproject.rx.RxUrlDefinition;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 电影数据Module
 *
 * @author 王杰
 */
public class MoviesModule {

    /**
     * 获取评价最高电影
     */
    public Subscription getHighestRatedMovies(Action1<MoviesListBean> success, Action1<Throwable> throwable) {
        return RXClientGenerator.getInstance().createClient()
                .getHighestRatedMovies(RxUrlDefinition.GET_HIGHEST_RATED_MOVIES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, throwable);
    }
}
