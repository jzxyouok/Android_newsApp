package com.nbd.view.main.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.aac.module.ui.AacActivity;
import com.aac.module.ui.AacFragment;
import com.aac.module.ui.AacFragmentPresenter;
import com.nbd.R;
import com.nbd.view.fragment.find.ui.FindFragment;
import com.nbd.view.fragment.news.ui.NewsFragment;
import com.nbd.view.fragment.user.ui.SelfFragment;
import com.nbd.view.login.ui.LoginActivity;

import java.util.Arrays;
import java.util.List;

import cn.like.nightmodel.NightModelManager;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends AacActivity {
    PageNavigationView pageNavigationView;
    NavigationController navigationController;
    List<AacFragment<? extends AacFragmentPresenter<? extends AacFragment<?>>>> fragments;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NightModelManager.getInstance().attach(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageNavigationView = (PageNavigationView) $(R.id.main_tab);
        navigationController = pageNavigationView.material()
                .addItem(R.mipmap.tab_news, R.mipmap.tab_news_click,getString(R.string.main_news))
                .addItem(R.mipmap.tab_find,R.mipmap.tab_find_click, getString(R.string.main_find))
                .addItem(R.mipmap.tab_self, R.mipmap.tab_self_click,getString(R.string.main_self))
                .setDefaultColor(getResources().getColor(R.color.textBlack))
                .build();
        fragments = Arrays.asList(new NewsFragment(), new FindFragment(), new SelfFragment());
        FragmentTransaction b = getSupportFragmentManager().beginTransaction();
        b.add(R.id.main_content, fragments.get(0));
        b.add(R.id.main_content, fragments.get(1));
        b.add(R.id.main_content, fragments.get(2));
        b.hide(fragments.get(1));
        b.hide(fragments.get(2));
        b.show(fragments.get(0));
        b.commitAllowingStateLoss();
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                //选中时触发
                update(index);
            }
            @Override
            public void onRepeat(int index) {
                //重复选中时触发
            }
        });
    }

    /***
     * @param index 选中缩索引
     **/
    private void update(int index) {
        FragmentTransaction b = getSupportFragmentManager().beginTransaction();
        for (Fragment item : fragments) {
            b.hide(item);
        }
        b.show(fragments.get(index));
        b.commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        NightModelManager.getInstance().detach(this);
        super.onDestroy();
    }

    /***
     * 切换调用
     * **/
    public   void changeNightModel() {
        if (NightModelManager.getInstance().isCurrentNightModel(this)) {
            NightModelManager.getInstance().applyDayModel(this);
        } else {
            NightModelManager.getInstance().applyNightModel(this);
        }
    }
}
