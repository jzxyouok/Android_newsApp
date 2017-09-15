package com.nbd.view.load.ui;


import android.content.Intent;
import android.os.Bundle;

import com.aac.module.ui.AacActivity;
import com.aac.module.pres.RequiresPresenter;
import com.nbd.R;
import com.nbd.view.main.ui.MainActivity;
import com.nbd.view.load.presenter.LoadPresenter;

/**
 * Created by Luca on 2017/9/8.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */
@RequiresPresenter(LoadPresenter.class)
public class LoadActivity extends AacActivity<LoadPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        $(R.id.splash_img).postDelayed(() -> {
            MainActivity.startActivity(LoadActivity.this);
            finish();
        },3000);

    }
}
