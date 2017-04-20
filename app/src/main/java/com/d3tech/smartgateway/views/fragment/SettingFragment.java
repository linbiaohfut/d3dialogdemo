/**
 * @(#)SettingFragment 1.0.0 2016-05-31 10:22
 * <p/>
 * Copyright 2016 Hangzhou PowerStone Technology CO.,LTD.  All rights reserved.
 * PowerStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.d3tech.smartgateway.views.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d3tech.smartgateway.R;
import com.d3tech.smartgateway.utils.App;
import com.d3tech.smartgateway.utils.FileUtil;
import com.d3tech.smartgateway.utils.IntentUtil;
import com.d3tech.smartgateway.utils.SPUtil;
import com.d3tech.smartgateway.utils.UI;

import java.io.File;

/**
 *
 * Description:设置页面
 * @author linzhuowei (lin_zhuowei@pstone.cc)
 * @version $Revision:1.0.0, $Date: 2016-05-31 10:22 
 * ${tags} 
 */
public class SettingFragment  extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    public static final String CLEAR_CACHE = "clear_cache";
    public static final String FEED_BACK = "feedback";
    public static final String APP_VERSION = "check_version";
    public static final String ORIGINAL_SPLASH = "original_splash";
    public static final String SECRET_MODE = "secret_mode";
    private static final long DURATION = 300;

    private Preference clearCache;
    private Preference about;
    private Preference version;
    private Preference splash;
    private CheckBoxPreference enableSister;
    private View rootView;

    private long startTime;
    private boolean first = true;
    private int secretIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        clearCache = findPreference(CLEAR_CACHE);
        about = findPreference(FEED_BACK);
        version = findPreference(APP_VERSION);
        splash = findPreference(ORIGINAL_SPLASH);
        clearCache.setSummary(clearCache.getSummary() + " " + getCacheSize());
        splash.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                secretStepOne();
                return true;
            }
        });
        version.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.i("test", secretIndex + ">>>>");
                if ((Boolean) newValue && secretIndex < 3) {
//                    BmobUpdateAgent.forceUpdate(getActivity());
                }
                secretStepTwo();
                return true;
            }
        });

        clearCache.setOnPreferenceClickListener(this);
        about.setOnPreferenceClickListener(this);
    }

    private void secretStepTwo() {
        if (System.currentTimeMillis() - startTime < DURATION * (secretIndex + 1)) {
            if (secretIndex > 2) {
                Log.i("test", "splash " + secretIndex);
                secretIndex++;
            }
        }
        if (secretIndex == 6) {

            secretIndex++;
        }
    }

    private void secretStepOne() {
        if (first) {
            startTime = System.currentTimeMillis();
            first = false;
        }
        if (System.currentTimeMillis() - startTime < DURATION * (secretIndex + 1)) {
            if (secretIndex < 3) {
                secretIndex++;
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (null == rootView) {
            rootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        return rootView;

    }

    private String getCacheSize() {
        File file = getActivity().getApplicationContext().getCacheDir();
        return FileUtil.getFileSize(file);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        switch (key) {
            case CLEAR_CACHE:
                App.openAppInfo(getActivity());
                break;
            case FEED_BACK:
                sendEmailFeedback();
                break;
        }
        return true;
    }


    private void sendEmailFeedback() {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        if (IntentUtil.isIntentSafe(email)) {
            email.setData(Uri.parse("mailto:danteandroi@gmail.com"));
            email.putExtra(Intent.EXTRA_SUBJECT, "Knowledge Feedback");
            email.putExtra(Intent.EXTRA_TEXT, "Hi，");
            startActivity(email);
        } else {
            //UI.showSnack(rootView, R.string.email_not_install);
        }
    }
}
