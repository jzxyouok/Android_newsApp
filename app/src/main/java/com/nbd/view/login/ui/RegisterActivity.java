package com.nbd.view.login.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aac.module.ui.AacActivity;
import com.aac.module.pres.RequiresPresenter;
import com.nbd.R;
import com.nbd.view.login.presenter.RegisterPresenter;
import com.yutils.TimeUtils;
import com.yutils.YUtils;

/**
 * Created by Luca on 2017/9/10.
 * E-Mail:yangzoucn@163.com
 * Deprecated: 注册UI
 */
@RequiresPresenter(RegisterPresenter.class)
public class RegisterActivity extends AacActivity<RegisterPresenter> implements View.OnClickListener {


    /**
     * 启动注册
     *
     * @param type 类型 0 注册 1 忘记
     **/
    public static void startActivity(Activity activity, int type) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    TextView btnCode, custom_title_text;
    EditText register_user_edit, register_password_edit;
    CountDownTimer countDownTimer;
    Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone_code);
        findViewById(R.id.custom_title_back_layout).setOnClickListener(this);
        btnCode = $(R.id.register_getphone_num_img);
        register_user_edit = $(R.id.register_user_edit);
        register_button = $(R.id.register_button);
        register_password_edit = $(R.id.register_password_edit);
        custom_title_text = $(R.id.custom_title_text);
        btnCode.setOnClickListener(this);
        register_button.setOnClickListener(this);

    }

    /***
     * 类型
     * @param  type 0 注册 1忘记
     **/
    public void showTitle(int type) {
        if (type == 0)
            custom_title_text.setText(R.string.register_hint);
        custom_title_text.setText(R.string.login_forget_pwd);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        if (R.id.custom_title_back_layout == v.getId()) {
            finish();
        } else if (v.getId() == R.id.register_getphone_num_img) {//发送验证码接口
            sendVCode();
        } else if (v.getId() == R.id.register_button) {
            registerBtn();
        }
    }


    /**
     * 发送验证码
     **/
    private void sendVCode() {
        String mobile = register_user_edit.getText().toString().trim();
        if (mobile.isEmpty()) {
            YUtils.Toast(R.string.hint_login_username);
        } else {
            btnCode.setEnabled(false);
            countDownTimer = TimeUtils.countDown(this, 60, 1, listener);
            //发送验证码
            getPresenter().sendCode(mobile);
        }
    }

    /***
     * 提交数据
     */
    private void registerBtn() {
        String mobile = register_user_edit.getText().toString().trim();
        String code = register_user_edit.getText().toString().trim();
        if (mobile.isEmpty()) {
            YUtils.Toast(R.string.hint_login_username);
        } else if (code.isEmpty()) {
            YUtils.Toast(R.string.register_code_input_hint);
        } else {
            getPresenter().submit(mobile, code);
        }
    }

    /***
     * 验证码回调接口
     */
    private TimeUtils.CountDownListener listener = new TimeUtils.CountDownListener() {
        @Override
        public void onFinish(String text) {
            btnCode.setEnabled(true);
            btnCode.setText(R.string.register_get_code);
        }

        @Override
        public void onTick(long millisUntilFinished, String text) {
            btnCode.setText(text);
        }
    };
}
