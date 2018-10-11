package com.example.smaboy.languageswitch;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

/**
 * 类名: SettingActivity
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/9/30 15:44
 */
public class SettingActivity extends AppCompatActivity {

    private String language;//本app选择的语言
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //获取本app使用的语言
        language = getSharedPreferences("share", MODE_PRIVATE).getString("local", "");

        //初始化
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        //设置页面
        if("zh-CN".equals(language)) {//简体中文
            btn2.setBackgroundColor(Color.RED);
            btn2.setTextColor(Color.WHITE);
        }else if("zh-TW".equals(language)) {//繁体中文
            btn3.setBackgroundColor(Color.RED);
            btn3.setTextColor(Color.WHITE);
        }else if("en-US".equals(language)) {//美式英语
            btn4.setBackgroundColor(Color.RED);
            btn4.setTextColor(Color.WHITE);
        }else {
            btn1.setBackgroundColor(Color.RED);
            btn1.setTextColor(Color.WHITE);
        }
    }

    public void autoClick(View view) {//自动

        LanguageManager.saveSelectLanguage(this,"");
        LanguageManager.changeAppLanguage(LanguageManager.getCurrentSystemLocal(),this);
        //重新启动Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void rCNClick(View view) {//简体中文
        LanguageManager.saveSelectLanguage(this,"zh-CN");
        LanguageManager.changeAppLanguage(Locale.SIMPLIFIED_CHINESE,this);
        //重新启动Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void rTWClick(View view) {//繁体中文
        LanguageManager.saveSelectLanguage(this,"zh-TW");
        LanguageManager.changeAppLanguage(Locale.TRADITIONAL_CHINESE,this);
        //重新启动Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void rUSClick(View view) {//英文
        LanguageManager.saveSelectLanguage(this,"en-US");
        LanguageManager.changeAppLanguage(Locale.US,this);
        //重新启动Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
