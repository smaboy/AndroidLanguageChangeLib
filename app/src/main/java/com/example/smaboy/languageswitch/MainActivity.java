package com.example.smaboy.languageswitch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;

    private BroadcastReceiver mReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals((Intent.ACTION_LOCALE_CHANGED))) {
                Log.e("TAG", "系统语言变化了为："+LanguageManager.getCurrentSystemLanguageCountry());
                //将变化后的系统语言保存到本地
                LanguageManager.saveSysLanguage(getApplicationContext(),LanguageManager.getCurrentSystemLanguageCountry());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册广播监听系统语言环境变化
        IntentFilter filter =new IntentFilter();
        filter.addAction(Intent.ACTION_LOCALE_CHANGED);
        registerReceiver(mReceiver, filter);


        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);

        //设置数据

        //重新赋值
        tv1.setText(getApplicationContext().getResources().getString(R.string.app_tips));

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

    public void lookSysLanguage(View view) {
        //查看当前手机的语言环境

        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("手机语言环境");
        alertDialog.setMessage(LanguageManager.getCurrentSystemLanguageCountry());
        alertDialog.show();

    }
}
