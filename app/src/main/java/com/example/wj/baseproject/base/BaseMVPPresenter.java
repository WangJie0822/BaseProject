package com.example.wj.baseproject.base;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Presenter基类
 *
 * @param <V> MVP View类型 继承{@link BaseMVPView}
 * @param <M> MVP Module 继承{@link BaseMVPModule}
 */
public class BaseMVPPresenter<V extends BaseMVPView, M extends BaseMVPModule> {

    protected V mView;

    @Inject
    protected M mModule;

    private CompositeDisposable disposables;

    public BaseMVPPresenter() {
        disposables = new CompositeDisposable();
    }

    public void onAttach(V view) {
        mView = view;
    }

    void onDetach() {
        mView = null;
    }

    /**
     * 检查请求返回数据，并在登录状态异常时弹出提示
     *
     * @param data 返回数据
     * @param <T>  返回数据类型
     *
     * @return 是否成功
     */
    protected <T extends BaseBean> boolean checkResponse(T data) {

        return true;
    }

    /**
     * RxJava生命周期
     */
    protected void addDisposable(Disposable dis) {
        if (dis != null) {
            disposables.add(dis);
        }
    }

    /**
     * 消费所有事件
     */
    void dispose() {
        if (!disposables.isDisposed() && disposables.size() > 0) {
            disposables.dispose();
        }
    }

}
