package com.nbd.view.search.ui;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import com.aac.module.ui.AacActivity;
import com.aac.module.pres.RequiresPresenter;
import com.nbd.R;
import com.nbd.view.search.presenter.SearchPresenter;

/**
 * Created by Luca on 2017/9/8.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */
@RequiresPresenter(SearchPresenter.class)
public class SearchActivity extends AacActivity<SearchPresenter> {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }
    TextView custom_title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        custom_title_text= $(R.id.custom_title_text);
        findViewById(R.id.custom_title_back_layout).setOnClickListener(v -> finish());
        custom_title_text.setText(R.string.search_hint);

    }
}
