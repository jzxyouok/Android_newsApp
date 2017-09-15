package com.nbd.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.aac.module.model.AacViewModel;
import com.nbd.config.HttpBase;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by Luca on 2017/9/10.
 * E-Mail:yangzoucn@163.com
 * Deprecated: app
 */

public class LoginViewModel extends AacViewModel {

    private MutableLiveData<Boolean> login;
    private MutableLiveData<Boolean> register;
    private MutableLiveData<String> sendCode;
    private MutableLiveData<Boolean> forgetPwd;

    @Override
    protected void onCleared() {
        super.onCleared();
        login = null;
        register = null;
        forgetPwd = null;
    }

    /***
     * 登录
     *
     * @param mobile   手机
     * @param passWord 密码
     ***/
    public LiveData<Boolean> login(String mobile, String passWord) {
        if (login == null)
            login = new MutableLiveData<>();
        //okhttp-utils进行网络请求， 成功调用login.setValue();
        OkHttpUtils
                .post()
                .url(HttpBase.LoginUrl)
                .addParams("app","user.login")
                .addParams("account",mobile)
                .addParams("password",passWord)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        progressDialog.hide();
//                        alertBox("登录失败：网络错误...",loginActivity.this);
                        Log.i("AAA","登录出错啦 傻x--｜--" + id);
                        Log.i("AAA","" + mobile + "|" + passWord);
                        login.setValue(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        progressDialog.hide();
                        //Log.i("AAA",response + "-----------|--"+ id);

                        try{
                            JSONObject dataJson = new JSONObject(response);
                            switch (dataJson.getString("code").toString()){
                                case "200":
//                                                alertBox("登录成功...",loginActivity.this);
//                                                Log.i("AAA","登录成功");
                                    //执行登录后操作，activity切换
//                                    Intent i = new Intent(loginActivity.this, settingsActivity.class);
//                                    startActivity(i);
                                    login.setValue(true);
                                    break;
                                case "-2":
//                                    alertBox("帐号不存在",loginActivity.this);
                                    login.setValue(false);
                                    break;
                                case "-3":
//                                    alertBox("帐号或密码错误",loginActivity.this);
                                    login.setValue(false);
                                    break;
                                default:
//                                    alertBox("网络错误，请重启...",loginActivity.this);
                                    login.setValue(false);
                            }
                        } catch (JSONException e) {
                            System.out.println("JSONException wrong...");
                            e.printStackTrace();
                        }
                    }
                });
        //可以异步处理 login.setValue(true);
        return login;
    }


    /***
     * 注册
     *
     * @param mobile 手机号
     ***/
    public LiveData<Boolean> getRegister(String mobile) {
        if (register == null)
            register = new MutableLiveData<>();
        //这个进行网络请求， 成功调用login，setValue();
        //可以异步处理
        register.setValue(true);
        return register;
    }

    /***
     * 注册
     *
     * @param mobile 手机号
     ***/
    public LiveData<Boolean> forgetPwd(String mobile) {
        if (forgetPwd == null)
            forgetPwd = new MutableLiveData<>();
        //这个进行网络请求， 成功调用login，setValue();
        //可以异步处理
        forgetPwd.setValue(true);
        return forgetPwd;
    }

    /***
     * 发送验证码
     *
     * @param mobile 手机号
     ***/
    public LiveData<String> senDsCode(String mobile) {
        if (sendCode == null)
            sendCode = new MutableLiveData<>();
        //这个进行网络请求， 成功调用login，setValue();
        //可以异步处理
        sendCode.setValue("12345");
        return sendCode;
    }
}
