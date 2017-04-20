package com.d3tech.smartgateway.presenter;

import android.os.Bundle;

import com.d3tech.smartgateway.views.activity.SplashActivity;

import nucleus.presenter.RxPresenter;
import rx.Observable;
import rx.functions.Func0;

/**
 * Created by linzhuowei on 2016/7/9.
 */
public class SplashPresenter extends RxPresenter<SplashActivity> {
    private static final int REQUEST_SPLASH = 1;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

    }


}
