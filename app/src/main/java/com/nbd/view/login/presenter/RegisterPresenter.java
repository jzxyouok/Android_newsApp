package com.nbd.view.login.presenter;


import com.aac.module.ui.AacPresenter;
import com.nbd.R;
import com.nbd.model.LoginViewModel;
import com.nbd.view.login.ui.RegisterActivity;
import com.yutils.YUtils;

/**
 * Created by Luca on 2017/9/8.
 * E-Mail:yangzoucn@163.com
 * Deprecated: 注册忘记页面
 */

public class RegisterPresenter extends AacPresenter<RegisterActivity> {
    private int type;//类型
    private LoginViewModel mLoginViewModel;
    private String code;//保存发送验证码
    @Override
    public void onCreate() {
        super.onCreate();
        type=getView().getIntent().getIntExtra("type",0);
        mLoginViewModel=getViewModel(LoginViewModel.class);
        getView().showTitle(type);
    }
    /***
     * 处理提交数据
     * @param mobile 手机
     * @param  code 验证码
     * **/
    public void submit(String mobile,String code) {
        if (this.code==null)return;
        if (code.equals(this.code)) {
            if (type == 0) {//注册
                mLoginViewModel.getRegister(mobile).observe(getView(),aBoolean -> {
                  //处理结果
                });
            } else {//忘记
                mLoginViewModel.forgetPwd(mobile).observe(getView(),aBoolean -> {
                    //处理结果。完成跳转界面
                });
            }
        }else {
            YUtils.Toast(R.string.register_code_input_hint_error);
        }
    }
    /***
     * 发送验证码
     * @param mobile 手机
     * **/
    public  void sendCode(String mobile){
        mLoginViewModel.senDsCode(mobile).observe(getView(),s -> {
            if (s!=null){
                code=s;
            }else {
                YUtils.Toast("发送失败");
            }
        });
    }
}
