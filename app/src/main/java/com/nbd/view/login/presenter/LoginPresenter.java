package com.nbd.view.login.presenter;

import android.arch.lifecycle.Observer;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;

import com.aac.module.ui.AacPresenter;
import com.nbd.R;
import com.nbd.model.LoginViewModel;
import com.nbd.view.login.ui.LoginActivity;
import com.yutils.YUtils;

/**
 * Created by Luca on 2017/9/8.
 * E-Mail:yangzoucn@163.com
 * Deprecated: 登录操作业务
 */

public class LoginPresenter extends AacPresenter<LoginActivity>{
    public static final String TAG = "LoginPresenter";
   private LoginViewModel loginViewModel;

    @Override
    protected void onCreate() {
        super.onCreate();
        loginViewModel=getViewModel(LoginViewModel.class);
    }

    /***
     * 登录操作
     * ***/
    public  void  login(String mobile, String pwd){
        loginViewModel.login(mobile,pwd).observe(getView(), b -> {
            if(b)
            {
//                    getView().finish();
                YUtils.Toast(R.string.login_suc);

            }
            else{
                YUtils.Toast(R.string.login_f);
            }
        });
    }
}
