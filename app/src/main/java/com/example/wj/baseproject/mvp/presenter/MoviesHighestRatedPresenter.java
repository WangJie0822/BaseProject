package com.example.wj.baseproject.mvp.presenter;

import com.example.wj.baseproject.base.BaseMVPPresenter;
import com.example.wj.baseproject.mvp.view.MoviesHighestRatedView;
import com.example.wj.baseproject.mvp.module.MoviesModule;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import rx.Subscription;

/**
 * 主界面Presenter
 *
 * @author 王杰
 */
public class MoviesHighestRatedPresenter extends BaseMVPPresenter<MoviesHighestRatedView, MoviesModule> {

    @Inject
    MoviesHighestRatedPresenter() {
        super();
        mModule = new MoviesModule();
    }

    public void getHighestRatedMovies() {

        Subscription sub = mModule.getHighestRatedMovies(
                data -> {

                    if (data == null) {
                        mView.showToast("The movies data is empty");
                        return;
                    }

                    mView.notifyData(data);

                }, throwable -> {
                    mView.showToast("Net Error!");
                    Logger.e(throwable, "net_error");
                });

        addSub(sub);
    }

}
