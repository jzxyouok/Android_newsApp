package com.nbd.app;

import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;

import com.yutils.YUtils;

import cn.like.nightmodel.NightModelManager;

/**
 * Created by Luca on 2017/9/8.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */

public class App  extends Application{
    public static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityCallback());
        YUtils.initialize(this);
        NightModelManager.getInstance().init(this);
    }
}
