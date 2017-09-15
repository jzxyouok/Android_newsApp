package com.nbd.view.fragment.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.aac.module.pres.RequiresPresenter;
import com.aac.module.ui.AacFragment;
import com.nbd.R;
import com.nbd.view.feedback.ui.FeedBackActivity;
import com.nbd.view.fragment.user.presenter.SelfPresenter;
import com.nbd.view.login.ui.LoginActivity;
import com.nbd.view.main.ui.MainActivity;
import com.nbd.view.settings.ui.SelfSettingsActivity;

import cn.like.nightmodel.NightModelManager;

/**
 * Created by Luca on 2017/9/10.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */

@RequiresPresenter(SelfPresenter.class)
public class SelfFragment extends AacFragment<SelfPresenter>  implements View.OnClickListener{
    public static final String TAG = SelfFragment.class.getName();

    ToggleButton self_center_night_toggle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_self_center,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
          $(view,R.id.self_center_login_text).setOnClickListener(this);
        self_center_night_toggle=$(view,R.id.self_center_night_toggle);
        $(view,R.id.self_center_feedback_layout).setOnClickListener(this);
        self_center_night_toggle.setOnClickListener(this);
        $(view,R.id.self_center_setting_layout).setOnClickListener(this);
        self_center_night_toggle.setChecked(NightModelManager.getInstance().isCurrentNightModel(getContext()));

    }

    @Override
    public void onClick(View v) {
        if (R.id.self_center_login_text==v.getId()){
            LoginActivity.startActivity(getActivity());
        }else if (v.getId()==R.id.self_center_night_toggle){
            ((MainActivity)getActivity()).changeNightModel();
        }else if (v.getId()==R.id.self_center_setting_layout){
            SelfSettingsActivity.startActivity(getActivity());
        }else if (v.getId()==R.id.self_center_feedback_layout){
            FeedBackActivity.startActivity(getActivity());
        }

    }

}
