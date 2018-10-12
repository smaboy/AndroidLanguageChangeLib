package com.example.smaboy.languageswitch;

import android.app.Application;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;

/**
 * 类名: MyApplication
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/8 14:19
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //获取系统语言，并保存
        LanguageManager.saveSysLanguage(getApplicationContext(),LanguageManager.getCurrentSystemLanguageCountry());

        //读取用户，语言并设置
        setLanguageConfiguration();


    }

    private void setLanguageConfiguration() {
        //初始化app语言环境
        if(!TextUtils.isEmpty(LanguageManager.getSelectLanguage(this))) {

            Locale locale=LanguageManager.getLocalByString(LanguageManager.getSelectLanguage(this));

            Log.e("TAG", "当前用户选择的语言为："+LanguageManager.getSelectLanguage(this));
            LanguageManager.changeAppLanguage(locale,this);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLanguageConfiguration();
    }
}
