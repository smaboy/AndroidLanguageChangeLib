package com.example.smaboy.languageswitch;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.smaboy.languageswitch.manager.LanguageManager;

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
        LanguageManager.saveSysLanguage(this,LanguageManager.getCurrentSystemLanguageCountry());

    }

}
