package com.input.qz.input;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.input.qz.input.view.SlideTabView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";
//    ViewPager mContentViewPager;
    SlideTabView mTabSegment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIRoundButton btn = findViewById(R.id.view_main_setting);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComponentName com = new ComponentName("com.android.settings", "com.android.settings.LanguageSettings");
                Intent intent = new Intent().setAction(Intent.ACTION_MAIN).setComponent(com);
                startActivity(intent);
            }
        });
//        QMUITopBar topBar = findViewById(R.id.view_main_topbar);
//        topBar.setTitle("QZBH");
//        mTabSegment = findViewById(R.id.view_main_tab);
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < 8; i++) {
//            list.add("Item " + (i + 1));
//        }
//        mTabSegment.initTab(list);
    }
    private Context getContext(){
        return this;
    }



}
