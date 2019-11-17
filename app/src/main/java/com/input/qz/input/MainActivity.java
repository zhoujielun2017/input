package com.input.qz.input;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.input.qz.input.view.SlideTabView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

//    ViewPager mContentViewPager;
    SlideTabView mTabSegment;
    ViewPager contentViewPager;

    int mCurrentItemCount=8;
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
        mTabSegment = findViewById(R.id.view_main_tab);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 8; i++) {
            list.add("ItemItemItemItemItem " + (i + 1));
        }
        mTabSegment.initTab(list);

    }
    private Context getContext(){
        return this;
    }

//    private void initTabAndPager() {
//        contentViewPager.setAdapter(mPagerAdapter);
//
//        for (int i = 0; i < 8; i++) {
//            mTabSegment.addTab(new QMUITabSegment.Tab("Item " + (i + 1)));
//        }
//        int space = QMUIDisplayHelper.dp2px(getContext(), 16);
//        mTabSegment.setHasIndicator(true);
//        mTabSegment.setMode(QMUITabSegment.MODE_SCROLLABLE);
//        mTabSegment.setItemSpaceInScrollMode(space);
//        mTabSegment.setupWithViewPager(contentViewPager, false);
//        mTabSegment.setPadding(space, 0, space, 0);
//
//    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return mCurrentItemCount;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
//            QDTabSegmentScrollableModeFragment.ContentPage page = QDTabSegmentScrollableModeFragment.ContentPage.getPage(position);
            View view =  new TextView(getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view, params);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            View view = (View) object;
            Object page = view.getTag();
            return POSITION_NONE;
        }
    };
}
