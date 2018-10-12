package com.example.smaboy.languageswitch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.example.smaboy.languageswitch.manager.LanguageManager;
import com.example.smaboy.languageswitch.R;
import com.example.smaboy.languageswitch.base.BaseActivity;

public class MainActivity extends BaseActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //初始化本地语言环境
        //初始化app语言环境
//        if(!TextUtils.isEmpty(LanguageManager.getSelectLanguage(this))) {
//
//            Locale locale=LanguageManager.getLocalByString(LanguageManager.getSelectLanguage(this));
//
//            Log.e("TAG", "当前用户选择的语言为："+LanguageManager.getSelectLanguage(this));
//            LanguageManager.changeAppLanguage(locale,this);
//        }

        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);

        //设置数据

        //重新赋值
        tv1.setText(getResources().getString(R.string.app_tips));

        //获取手机系统语言环境
        String language = LanguageManager.getSysLanguage(this);
        if("zh-CN".equals(language)) {//简体中文
            tv2.setText("简体中文");
        }else if("zh-TW".equals(language)) {//繁体中文
            tv2.setText("繁体中文");
        }else if("en-US".equals(language)) {//美式英语
            tv2.setText("美式英语");
        }

        //获取app当前语言环境,默认情况下为简体中文
        String local = LanguageManager.getSelectLanguage(this);
        if("zh-CN".equals(local)) {//简体中文
            tv3.setText("简体中文");
        }else if("zh-TW".equals(local)) {//繁体中文
            tv3.setText("繁体中文");
        }else if("en-US".equals(local)) {//美式英语
            tv3.setText("英语");
        }else {
            tv3.setText("自动");
        }

    }

    public void btnClick(View view) {

        //点击设置进入语言设置界面
        startActivity(new Intent(this,SettingActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        //页面重新展示的时候。检测语言环境
        String local = getSharedPreferences("share", MODE_PRIVATE).getString("local", "zh-CN");
    }

}
