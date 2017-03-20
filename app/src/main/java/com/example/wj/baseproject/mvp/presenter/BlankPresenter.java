package com.example.wj.baseproject.mvp.presenter;

import com.example.wj.baseproject.base.BaseMVPPresenter;
import com.example.wj.baseproject.base.BaseMVPView;
import com.example.wj.baseproject.mvp.module.MoviesModule;

import javax.inject.Inject;

/**
 * 空白的Presenter
 *
 * @author 王杰
 */
public class BlankPresenter extends BaseMVPPresenter<BaseMVPView, MoviesModule> {

    @Inject
    BlankPresenter() {
    }
}
