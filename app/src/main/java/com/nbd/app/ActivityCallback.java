package com.nbd.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import com.aac.module.callback.ActivityLifecycleCallbacksWrapper;
import com.nbd.view.main.ui.MainActivity;


/**
 * Created by Luca on 2017/9/8.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */
public class ActivityCallback extends ActivityLifecycleCallbacksWrapper {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        super.onActivityCreated(activity, savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(false);
        Resources res = activity.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());


    }


    @Override
    public void onActivityDestroyed(Activity activity) {
        super.onActivityDestroyed(activity);
    }

}
