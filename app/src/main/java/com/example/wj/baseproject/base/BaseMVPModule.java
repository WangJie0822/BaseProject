package com.example.wj.baseproject.base;

import com.example.wj.baseproject.net.RXApi;

import javax.inject.Inject;

/**
 * MVP Module基类
 */
public class BaseMVPModule {

    @Inject
    protected RXApi netClient;

    @Inject
    protected BaseMVPModule() {
    }
}
