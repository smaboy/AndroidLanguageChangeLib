package com.example.smaboy.languageswitch;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
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

        Log.e("tag", "当前系统语言为：" + language);


        return locale;

    }

    /**
     * 返回系统语言
     * 如 zh 中文
     * en 英文
     *
     * @return
     */
    public static String getCurrentSystemLanguage() {
        Locale locale = getCurrentSystemLocal();

        return locale.getLanguage();

    }

    /**
     * 返回系统语言及国家代码
     * 如 zh-CN中文简体
     * zh-TW中文繁体
     * en-US美式英文
     *
     * @return
     */
    public static String getCurrentSystemLanguageCountry() {
        Locale locale = getCurrentSystemLocal();

        return locale.getLanguage() + "-" + locale.getCountry();

    }

    /**
     * 更改应用语言
     *
     * @param locale
     */
    public static void changeAppLanguage(Locale locale, Context context) {

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        // app locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }

        // updateConfiguration
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);
    }


    /**
     * 设置语言类型
     */
    public static void setApplicationLanguage(Context context, Locale locale) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.getApplicationContext().createConfigurationContext(config);
            Locale.setDefault(locale);
        }
        resources.updateConfiguration(config, dm);
    }

    /**
     * 将zh-CN等转化为Local
     *
     * @param language
     * @return
     */
    public static Locale getLocalByString(String language) {

        if ("zh-CN".equals(language)) {//简体中文
            return Locale.SIMPLIFIED_CHINESE;
        } else if ("zh-TW".equals(language)) {//繁体中文
            return Locale.TRADITIONAL_CHINESE;
        } else if ("en-US".equals(language)) {//美式英语
            return Locale.US;
        } else {
            return Locale.SIMPLIFIED_CHINESE;
        }

    }

    /**
     * 将用户选择的语言。保存到本地，进行持久化
     *
     * @param language
     * @return
     */
    public static void saveSelectLanguage(Context context, String language) {

        context.getSharedPreferences("share", Context.MODE_PRIVATE).edit().putString("local", language).apply();

    }

    /**
     * 取出用户保存的语言
     *
     * @return
     */
    public static String getSelectLanguage(Context context) {

        return context.getSharedPreferences("share", Context.MODE_PRIVATE).getString("local", "");

    }


}
