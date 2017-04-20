/**
 * @(#)GatewayApplication 1.0.0 2016-05-30 18:06
 * <p/>
 * Copyright 2016 Hangzhou PowerStone Technology CO.,LTD.  All rights reserved.
 * PowerStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.d3tech.smartgateway;

import android.app.Application;
import android.content.Context;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 *
 * Description:
 * @author linzhuowei (lin_zhuowei@pstone.cc)
 * @version $Revision:1.0.0, $Date: 2016-05-30 18:06 
 * ${tags} 
 */
public class GatewayApplication extends Application {
    private RefWatcher refWatcher;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        refWatcher = LeakCanary.install(this);
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }

    public static RefWatcher getWatcher(Context context) {
        GatewayApplication application = (GatewayApplication) context.getApplicationContext();
        return application.refWatcher;
    }


    public class AppBlockCanaryContext extends BlockCanaryContext {
        // override to provide context like app qualifier, uid, network type, block threshold, log save path
        // this is default block threshold, you can set it by phone's performance
        @Override
        public int getConfigBlockThreshold() {
            return 500;
        }

        // if set true, notification will be shown, else only write log file
        @Override
        public boolean isNeedDisplay() {
            return BuildConfig.DEBUG;
        }

        // path to save log file
        @Override
        public String getLogPath() {
            return "/blockcanary/performance";
        }
    }
}
