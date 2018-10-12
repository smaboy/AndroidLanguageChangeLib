package com.example.smaboy.languageswitch.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.smaboy.languageswitch.base.BaseActivity;
import com.example.smaboy.languageswitch.manager.LanguageManager;


/**
 * 类名: LocalChangedBroadcastReceiver
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/12 10:48
 */
public class LocalChangedBroadcastReceiver extends BroadcastReceiver {
    private Context mContext;
    public LocalChangedBroadcastReceiver(BaseActivity baseActivity) {
        mContext=baseActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //处理系统语言变化之后的处理
        if(intent.getAction().equals((Intent.ACTION_LOCALE_CHANGED))) {
            Log.e("TAG", "系统语言变化了");
            //将变化后的系统语言保存到本地
            LanguageManager.saveSysLanguage(mContext,LanguageManager.getCurrentSystemLanguageCountry());
        }
    }
}
