package com.example.wj.baseproject.mvp.presenter;

import com.example.wj.baseproject.base.BaseMVPPresenter;
import com.example.wj.baseproject.mvp.module.MoviesModule;
import com.example.wj.baseproject.mvp.view.MoviesHighestRatedView;
import com.example.wj.baseproject.util.ToastUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * 主界面Presenter
 *
 * @author 王杰
 */
public class MoviesHighestRatedPresenter extends BaseMVPPresenter<MoviesHighestRatedView, MoviesModule> {

    @Inject
    MoviesHighestRatedPresenter() {
        super();
    }

    public void getHighestRatedMovies() {

        Disposable disposable = mModule.getHighestRatedMovies(
                data -> {

                    if (data == null) {
                        ToastUtil.show("The movies data is empty");
                        return;
                    }

                    mView.notifyData(data);

                }, throwable -> {
                    ToastUtil.show("Net Error!");
                    Logger.e(throwable, "net_error");
                });

        addDisposable(disposable);
    }
}
