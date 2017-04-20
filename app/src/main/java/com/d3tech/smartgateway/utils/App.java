/**
 * @(#)App 1.0.0 2016-05-31 11:52
 * <p/>
 * Copyright 2016 Hangzhou PowerStone Technology CO.,LTD.  All rights reserved.
 * PowerStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.d3tech.smartgateway.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.d3tech.smartgateway.GatewayApplication;
import com.d3tech.smartgateway.R;

import java.io.File;

/**
 *
 * Description:
 * @author linzhuowei (lin_zhuowei@pstone.cc)
 * @version $Revision:1.0.0, $Date: 2016-05-31 11:52 
 * ${tags} 
 */
public class App {
    public static String getVersionName() {
        try {
            PackageManager manager = GatewayApplication.context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(GatewayApplication.context.getPackageName(), 0);
            String version = info.versionName;
            return /*GatewayApplication.context.getString(R.string.version) +*/ version;
        } catch (Exception e) {
            e.printStackTrace();
            return /*GatewayApplication.context.getString(R.string.can_not_find_version_name)*/null;
        }
    }

    public static void openAppInfo(Context context) {
        //redirect user to app Settings
        Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getApplicationContext().getPackageName()));
        context.startActivity(i);
    }

    public static boolean clearCache() {
        //this method does not work on cacheDir
        // but works for fileDir, don't know why
        File cacheDir = GatewayApplication.context.getCacheDir();
        for (File file : cacheDir.listFiles()) {
            if (!file.delete()) {
                return false;
            }
        }
        return true;
    }
}
