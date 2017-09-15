package com.nbd.view.settings.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.aac.module.ui.AacActivity;
import com.aac.module.pres.RequiresPresenter;
import com.nbd.R;
import com.nbd.view.settings.presenter.activity_setting_app_info;

/**
 * Created by Luca on 2017/9/10.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */
@RequiresPresenter(activity_setting_app_info.class)
public class SelfSettingsActivity extends AacActivity<activity_setting_app_info> {


    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, SelfSettingsActivity.class);
        activity.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_app_info);
        findViewById(R.id.custom_title_back_layout).setOnClickListener(v ->  finish());

    }


}
