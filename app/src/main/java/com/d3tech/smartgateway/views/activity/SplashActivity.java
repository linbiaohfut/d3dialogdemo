/**
 * @(#)SplashActivity 1.0.0 2016-05-31 9:47
 * <p/>
 * Copyright 2016 Hangzhou PowerStone Technology CO.,LTD.  All rights reserved.
 * PowerStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.d3tech.smartgateway.views.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.d3tech.smartgateway.R;
import com.d3tech.smartgateway.utils.SPUtil;
import com.d3tech.smartgateway.views.fragment.SettingFragment;

import org.thanatos.base.ui.activity.BaseActivity;


/**
 *
 * Description:
 * @author linzhuowei (lin_zhuowei@pstone.cc)
 * @version $Revision:1.0.0, $Date: 2016-05-31 9:47 
 * ${tags} 
 */
public class SplashActivity extends BaseActivity{
    private static final int SPLASH_DURATION = 2500;
    private static final String SPLASH = "splash";
    private ImageView splash;
    private String today;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        splash = (ImageView) findViewById(R.id.splash);
        if (SPUtil.getBoolean(SettingFragment.ORIGINAL_SPLASH)) {
            Glide.with(this).load(R.drawable.welcome).crossFade(1500).into(splash);
            startAppDelay();
            return;
        }
        initSplash();
    }

    private void initSplash() {
        /*today = DateUtil.parseStandardDate(new Date());
        loadImageFile();
        if (!today.equals(SPUtil.getString(Constants.DATE))) {
            getSplash();
        }*/
    }


    private void getSplash() {
        /*if (!Net.isOnline(this)) {
            return;
        }

        Net.get(API.SPLASH, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                startApp();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String url = jsonObject.getString("img");
                    SPUtil.save(SPLASH, url);
                    SPUtil.save(Constants.DATE, today);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, API.TAG_SPLASH);*/
    }

    private void loadImageFile() {
        /*String url = SPUtil.get(SPLASH, "");
        if ("".equals(url)) {
            Glide.with(this).load(R.drawable.welcome).crossFade(SPLASH_DURATION).into(splash);
        } else {
            Imager.load(url, R.anim.splash_anim, splash);
        }
        startAppDelay();*/
    }

    private void startAppDelay() {
        splash.postDelayed(new Runnable() {
            @Override
            public void run() {
                startApp();
            }
        }, SPLASH_DURATION);
    }

    private void startApp() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_grow_fade_in_from_bottom, android.support.v7.appcompat.R.anim.abc_shrink_fade_out_from_bottom);
        finish();
    }

    @Override
    public void onBackPressed() {
        //disable back button when showing splash
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
