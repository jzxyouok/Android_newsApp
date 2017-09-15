package com.nbd.view.feedback.ui;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.aac.module.ui.AacActivity;
import com.aac.module.pres.RequiresPresenter;
import com.nbd.R;
import com.nbd.view.feedback.presenter.FeedBackPresenter;

/**
 * Created by Luca on 2017/9/8.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */
@RequiresPresenter(FeedBackPresenter.class)
public class FeedBackActivity extends AacActivity<FeedBackPresenter> {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, FeedBackActivity.class);
        activity.startActivity(intent);
    }

    TextView custom_title_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        custom_title_text=$(R.id.custom_title_text);
         findViewById(R.id.custom_title_back_layout).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FeedBackActivity.this.finish();
             }
         });
        custom_title_text.setText(R.string.comments_and_feedback_hint);
    }
}
