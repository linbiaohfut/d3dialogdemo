/**
 * @(#)IntentUtil 1.0.0 2016-05-31 11:54
 * <p/>
 * Copyright 2016 Hangzhou PowerStone Technology CO.,LTD.  All rights reserved.
 * PowerStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.d3tech.smartgateway.utils;

import android.content.Intent;
import android.content.pm.PackageManager;

import com.d3tech.smartgateway.GatewayApplication;

import java.util.List;

/**
 *
 * Description:
 * @author linzhuowei (lin_zhuowei@pstone.cc)
 * @version $Revision:1.0.0, $Date: 2016-05-31 11:54 
 * ${tags} 
 */
public class IntentUtil {
    public static boolean isIntentSafe(Intent intent) {
        PackageManager packageManager = GatewayApplication.context.getPackageManager();
        List activities = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return activities.size() > 0;
    }
}
