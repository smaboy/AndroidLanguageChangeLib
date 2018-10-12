package com.example.smaboy.languageswitch.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.example.smaboy.languageswitch.manager.LanguageManager;
import com.example.smaboy.languageswitch.receiver.LocalChangedBroadcastReceiver;

/**
 * 类名: BaseActivity
 * 类作用描述: 基类activity
 * 作者: Smaboy
 * 创建时间: 2018/10/12 10:39
 */
public class BaseActivity extends AppCompatActivity {




    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册广播监听系统语言环境变化
        IntentFilter filter =new IntentFilter();
        filter.addAction(Intent.ACTION_LOCALE_CHANGED);
        registerReceiver(new LocalChangedBroadcastReceiver(this), filter);

        //处理本app语言初始化
        if(!TextUtils.isEmpty(LanguageManager.getSelectLanguage(this))){//如果用户选择了语言，就得强行将本app语言同步为用户选择的语言

            Log.e("TAG", "正在基类activity中初始化用户语言环境");
            //获取用户选择的语言
            String language=LanguageManager.getSelectLanguage(this);
            Log.e("TAG", "当前用户语言为："+language);
            //设置app语言
            LanguageManager.changeAppLanguage(LanguageManager.getLocalByString(language),this);

        } else {//如果，用户没用选择语言，或者选择了自动（即跟随系统），不作处理
            Log.e("TAG", "正在基类activity中跟随系统语言环境，不做处理");
        }
    }

}
