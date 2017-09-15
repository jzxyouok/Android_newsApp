package com.nbd.view.fragment.find.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aac.module.pres.RequiresPresenter;
import com.aac.module.ui.AacFragment;
import com.nbd.R;
import com.nbd.view.fragment.find.presenter.FindPresenter;
import com.nbd.view.search.ui.SearchActivity;

/**
 * Created by Luca on 2017/9/8.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */

@RequiresPresenter(FindPresenter.class)
public class FindFragment extends AacFragment<FindPresenter> {
    public static final String TAG = FindFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        $(view,R.id.logo_title_right_layout).setOnClickListener(v -> SearchActivity.startActivity(getActivity()));
    }
}
