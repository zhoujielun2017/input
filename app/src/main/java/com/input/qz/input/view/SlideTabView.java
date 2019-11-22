package com.input.qz.input.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.input.qz.input.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建： dongshuaijun .
 * 日期：2016/6/21.
 * 注释：顶部滑动标签栏
 */
public class SlideTabView extends HorizontalScrollView{

    public static final String TAG="SlideTabView";
    //标签布局容器
    private LinearLayout linearLayout;
    //指示器画笔
    private Paint paint;
    //tab容器
    private List<String> list;
    //text容器
    private List<TextView> textViews;
    //当前位置
    private int currentIndex = 0;
    //上一次位置
    private int lastIndex = 0;
    //满屏显示数量
    private float maxCount = 4.1f;
    //偏移百分比
    private float offSet;
    //非选中字体颜色
    private int noCurrColor = R.color.qmui_config_color_60_pure_black;
    //选中字体颜色
    private int currColor = R.color.qmui_config_color_50_blue;
    /**
     * item的默认字体大小
     */
    private int mTabTextSize = 18;
    //上下文
    private Context context;

    private OnTabSelectedListener mSelectedListeners;

    public SlideTabView(Context context) {
        this(context, null);
    }

    public SlideTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SlideTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context,attrs,defStyleAttr);
    }

    //初始化View
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        mTabTextSize =16;
        textViews = new ArrayList<>();

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 0, 10, 0);
        linearLayout.setLayoutParams(params);
        addView(linearLayout);
    }

    //初始化tab数据
    public void initTab(List<String> list) {
        this.list = list;
        reset();
        addTab();
    }


    //添加tab 默认选中第一个
    private void addTab() {
        for (int i = 0; i < list.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final TextView textView = new TextView(context);
            if (i == 0) {
                textView.setTextColor(getResources().getColor(currColor));
            } else {
                textView.setTextColor(getResources().getColor(noCurrColor));
            }
            layoutParams.setMargins(10, 20, 10, 20);
//            layoutParams.width = (int) (((float) getWth()) / maxCount);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(mTabTextSize);
            textView.setText(list.get(i));

            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentIndex=finalI;
                    refresh(currentIndex);
                    Log.i(TAG,currentIndex+"");
                    if(null!=mSelectedListeners){
                        mSelectedListeners.onTabSelected(currentIndex);
                    }
                }
            });
            textViews.add(textView);
            linearLayout.addView(textView);
        }
        invalidate();
    }

    public CharSequence getTabText(int index){
        if(textViews.size()>index){
            return textViews.get(index).getText();
        }else{
            return null;
        }
    }

    public void updateTabText(int index,CharSequence text){
        textViews.get(index).setText(text);
    }

    public void reset(){
        this.currentIndex=0;
        this.textViews.clear();
        linearLayout.removeAllViews();
    }

    private void refresh(int index) {
        for (int i = 0; i < textViews.size(); i++) {
            if (i != index) {
                textViews.get(i).setTextColor(getResources().getColor(noCurrColor));
            } else {
                textViews.get(i).setTextColor(getResources().getColor(currColor));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(textViews.isEmpty()){
            return;
        }
        float h = getHeight();

//        paint.setColor(getResources().getColor(offLineColor));
        View view = linearLayout.getChildAt(currentIndex);
        if(null==view){
            Log.w(TAG,currentIndex+"");
            return;
        }
        float lineLeft = view.getLeft();
        float lineRight = view.getRight();

        if (offSet > 0f) {
            View nextTab = linearLayout.getChildAt(currentIndex + 1);
            final float nextTabLeft = nextTab.getLeft();
            final float nextTabRight = nextTab.getRight();

            lineLeft = (offSet * nextTabLeft + (1f - offSet) * lineLeft);
            lineRight = (offSet * nextTabRight + (1f - offSet) * lineRight);
        }
        canvas.drawRect(lineLeft, h - 5, lineRight, h, paint);
    }


    public void addOnTabSelectedListener(@NonNull OnTabSelectedListener listener) {
        this.mSelectedListeners=listener;
    }


    public void setTabTextSize(int tabTextSize) {
        mTabTextSize = tabTextSize;
    }


    public void setCurrColor(int currColor) {
        this.currColor = currColor;
    }

    public void setNoCurrColor(int noCurrColor) {
        this.noCurrColor = noCurrColor;
    }


}
