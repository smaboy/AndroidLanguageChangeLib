package com.example.smaboy.languageswitch;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

/**
 * 类名: LanguageManager
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/9/30 16:04
 */
public class LanguageManager {


    public static Locale getCurrentSystemLocal() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0以上，手机可以是多语言系统
            locale = LocaleList.getDefault().get(0);


        } else {
            locale = Locale.getDefault();
        }



        String language = locale.getLanguage() + "-" + locale.getCountry();

        Log.e("tag","当前系统语言为："+language);


        return locale;

    }

    /**
     *返回系统语言
     *       如 zh 中文
     *          en 英文
     * @return
     */
    public static String getCurrentSystemLanguage() {
        Locale locale=getCurrentSystemLocal();

        return locale.getLanguage();

    }

    /**
     *
     * 返回系统语言及国家代码
     * 如 zh-CN中文简体
     *    zh-TW中文繁体
     *    en-US美式英文
     *
     * @return
     */
    public static String getCurrentSystemLanguageCountry() {
        Locale locale=getCurrentSystemLocal();

        return locale.getLanguage() + "-" + locale.getCountry();

    }

    /**
     * 更改应用语言
     *
     * @param locale
     */
    public static void changeAppLanguage(Locale locale ,Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        context.getResources().updateConfiguration(configuration, metrics);

    }

}
