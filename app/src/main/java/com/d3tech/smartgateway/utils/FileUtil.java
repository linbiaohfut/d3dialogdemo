/**
 * @(#)FileUtil 1.0.0 2016-05-31 10:30
 * <p/>
 * Copyright 2016 Hangzhou PowerStone Technology CO.,LTD.  All rights reserved.
 * PowerStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.d3tech.smartgateway.utils;

import java.math.BigDecimal;

/**
 *
 * Description:
 * @author linzhuowei (lin_zhuowei@pstone.cc)
 * @version $Revision:1.0.0, $Date: 2016-05-31 10:30 
 * ${tags} 
 */
public class FileUtil {
    public static long getFileLength(java.io.File dir) {
        long length = 0;
        for (java.io.File file :
                dir.listFiles()) {
            if (file.isFile()) {
                length += file.length();
            } else
                length += getFileLength(file);
        }
        return length;
    }

    public static String getFileSize(java.io.File dir) {
        BigDecimal bd;
        if (getFileLength(dir) > 1024 * 1024) {
            bd = new BigDecimal(getFileLength(dir) / 1000000);
            return bd.setScale(2, BigDecimal.ROUND_HALF_EVEN) + " M";

        } else {
            //length of file is less than 1 mb, use K as a unit
            bd = new BigDecimal(getFileLength(dir) / 1000);
            return bd.setScale(0, BigDecimal.ROUND_HALF_EVEN) + " k";

        }
    }
}
