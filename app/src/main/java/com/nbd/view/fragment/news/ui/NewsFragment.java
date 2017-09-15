package com.nbd.view.fragment.news.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aac.module.pres.RequiresPresenter;
import com.aac.module.ui.AacFragment;
import com.nbd.R;
import com.nbd.view.fragment.news.presenter.NewPresenter;
import com.nbd.view.search.ui.SearchActivity;

/**
 * Created by Luca on 2017/9/10.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */

@RequiresPresenter(NewPresenter.class)
public class NewsFragment extends AacFragment<NewPresenter> {
    public static final String TAG = NewsFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        $(view,R.id.logo_title_right_layout).setOnClickListener(v -> SearchActivity.startActivity(getActivity()));
    }
}
