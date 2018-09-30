package com.example.smaboy.languageswitch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 类名: SettingActivity
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/9/30 15:44
 */
public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void autoClick(View view) {//自动

    }

    public void rCNClick(View view) {//简体中文
    }

    public void rTWClick(View view) {//繁体中文
    }

    public void rUSClick(View view) {//英文
    }
}
