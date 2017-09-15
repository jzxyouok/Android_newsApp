package com.nbd.view.login.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.aac.module.pres.RequiresPresenter;
import com.aac.module.ui.AacActivity;
import com.nbd.R;
import com.nbd.utils.AndroidWorkaround;
import com.nbd.view.login.presenter.LoginPresenter;
import com.yutils.YUtils;

/**
 * A login screen that offers login via email/password.
 */
@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends AacActivity<LoginPresenter> {
    private static final String TAG = "LoginActivity";
    private ImageView logo, iv_clean_phone, clean_password, iv_show_pwd;
    private ScrollView scrollView;
    private EditText et_mobile,et_password;
    private Button btn_login;
    TextView custom_title_text;
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置输入法不弹起
        setContentView(R.layout.activity_login);
        AndroidWorkaround.assistActivity(this);
        intiView();
        initListener();
    }

    private void intiView() {
        logo = $(R.id.logo);
        scrollView = $(R.id.scrollView);
        et_mobile = $(R.id.et_mobile);
        et_password = $(R.id.et_password);
        iv_clean_phone = $(R.id.iv_clean_phone);
        clean_password = $(R.id.clean_password);
        iv_show_pwd = $(R.id.iv_show_pwd);
        btn_login = $(R.id.btn_login);
        custom_title_text = $(R.id.custom_title_text);
        keyHeight = YUtils.getScreenHeight() / 3;//弹起高度为屏幕高度的1/3
        custom_title_text.setText(R.string.login);
        findViewById(R.id.custom_title_back_layout).setOnClickListener(v -> finish());
    }

    private void initListener() {
        findViewById(R.id.forget_password).setOnClickListener(v -> RegisterActivity.startActivity(this, 1));
        findViewById(R.id.regist).setOnClickListener(v -> RegisterActivity.startActivity(this, 0));
        iv_clean_phone.setOnClickListener(v -> et_mobile.setText(""));
        iv_show_pwd.setOnClickListener(v -> et_password.setText(""));
        btn_login.setOnClickListener(v -> verNumber());
        clean_password.setOnClickListener(v -> cleanPwd());
        et_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && iv_clean_phone.getVisibility() == View.GONE) {
                    iv_clean_phone.setVisibility(View.VISIBLE);
                    btn_login.setEnabled(true);
                } else if (TextUtils.isEmpty(s)) {
                    btn_login.setEnabled(false);
                    iv_clean_phone.setVisibility(View.GONE);
                }
            }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && clean_password.getVisibility() == View.GONE) {
                    clean_password.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    clean_password.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    YUtils.Toast(R.string.please_input_limit_pwd);
                    s.delete(temp.length() - 1, temp.length());
                    et_password.setSelection(s.length());
                }
            }
        });
        // scrollView.setOnTouchListener((v, event) -> true);
        findViewById(R.id.root).addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
      现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
            if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                Log.e(TAG, "up------>" + (oldBottom - bottom));
                new Handler().postDelayed(() -> scrollView.smoothScrollTo(0, scrollView.getHeight()), 0);
                zoomIn(logo, (oldBottom - bottom) - keyHeight);
            } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                Log.e(TAG, "down------>" + (bottom - oldBottom));
                //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                new Handler().postDelayed(() -> scrollView.smoothScrollTo(0, scrollView.getHeight()), 0);
                zoomOut(logo, (bottom - oldBottom) - keyHeight);
            }
        });
    }

    /**
     * 缩小
     *
     * @param view 需要处理view
     * @param dist 缩小的
     */
    public void zoomIn(final View view, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist + 100);
        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(200);
        mAnimatorSet.start();
    }

    /**
     * 放大
     *
     * @param view 需要处理view
     * @param dist 缩小的
     */
    public void zoomOut(final View view, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(200);
        mAnimatorSet.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // zoomOut(logo, 0);

    }


    /***
     *清除密码
     *  **/
    private  void  cleanPwd(){
        if (et_password.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            iv_show_pwd.setImageResource(R.drawable.ic_pass_visuable);
        } else {
            et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            iv_show_pwd.setImageResource(R.drawable.ic_pass_gone);
        }
        String pwd = et_password.getText().toString();
        if (!TextUtils.isEmpty(pwd))
            et_password.setSelection(pwd.length());
    }
    /***
     * 验证账号
     ***/
    private void verNumber() {
        if (et_mobile.getText().toString().isEmpty()) {
            YUtils.Toast(R.string.hint_login_username);
        } else if (et_password.getText().toString().isEmpty()) {
            YUtils.Toast(R.string.hint_login_password);
        } else if (!et_mobile.getText().toString().matches("[A-Za-z0-9]+")) {
            YUtils.Toast(R.string.please_input_limit_pwd);
        } else if (et_password.getText().toString().length() < 6) {
            YUtils.Toast(R.string.please_input_limit_pwd_length);
        } else {
            YUtils.closeInputMethod(et_mobile);///实现登录业务
            getPresenter().login(et_mobile.getText().toString(), et_password.getText().toString());

        }
    }

}

