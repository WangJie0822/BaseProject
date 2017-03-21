package com.example.wj.baseproject.base;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * MVP Presenter基类
 *
 * @param <V> MVP View 继承 {@link BaseMVPView}
 * @param <M> MVP Module
 *
 * @author 王杰
 */
public class BaseMVPPresenter<V extends BaseMVPView, M> {

    /** View对象 */
    protected V mView;
    /** Module对象 */
    @Inject
    protected M mModule;

    /** Rx声明周期管理 */
    private CompositeSubscription subscriptions;

    @Inject
    protected BaseMVPPresenter() {
        subscriptions = new CompositeSubscription();
    }

    /**
     * 绑定View
     *
     * @param view MVP View
     */
    public void onAttach(V view) {
        mView = view;
    }

    /**
     * 解绑MVP View
     */
    void onDetach() {
        mView = null;
    }

    /**
     * 添加到生命周期管理
     *
     * @param sub 订阅者对象
     */
    protected void addSub(Subscription sub) {
        if (subscriptions == null) {
            return;
        }

        if (sub != null) {
            subscriptions.add(sub);
        }
    }

    /**
     * 解绑订阅者
     */
    void unSubscribe() {
        if (subscriptions == null) {
            return;
        }

        if (subscriptions.hasSubscriptions()) {
            subscriptions.unsubscribe();
        }
    }
}
