package com.input.qz.input.service;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.input.qz.input.MyApp;
import com.input.qz.input.R;
import com.input.qz.input.constants.KeyboardEnum;
import com.input.qz.input.constants.QZKeyCode;
import com.input.qz.input.service.impl.ChinaOnKeyAdapter;
import com.input.qz.input.service.impl.LetterOnKeyAdapter;
import com.input.qz.input.service.impl.QZKeyboard;
import com.input.qz.input.util.QZUtil;

import java.util.List;

/**
 * 类说明: 自定义键盘
 *
 * @author qing
 * @time 31/01/2018 17:57
 */
public class QZKeyboardView extends KeyboardView {

    //点击键盘时 按下的位置坐标
    private float mDownX;
    private float mDownY;

    // 数字键盘／字母键盘／符号键盘
    private QZKeyboard mKeyboardNum,mKeyboardUpperLetter,mKeyboardLetter, mKeyboardSymbol,mKeyboardChina;
    private QZKeyboard currentKeyboard;
    //整个view的引用
    private View view;

    public QZKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initKeyboardView(context);
    }

    public QZKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initKeyboardView(context);
    }

    /**
     * 切换数字键盘
     */
    public void switchKeyBoardNum(){
        currentKeyboard=mKeyboardNum;
        setKeyboard(mKeyboardNum.getKeyboard());
    }

    /**
     * 切换字母键盘
     */
    public void switchKeyBoardLetter(boolean upper){
        if(upper){
            currentKeyboard=mKeyboardUpperLetter;
            setKeyboard(mKeyboardUpperLetter.getKeyboard());
        }else{
            currentKeyboard=mKeyboardLetter;
            setKeyboard(mKeyboardLetter.getKeyboard());
        }
    }

    /**
     * 切换符号键盘
     */
    public void switchKeyBoardSymbol(){
        currentKeyboard=mKeyboardSymbol;
        setKeyboard(mKeyboardSymbol.getKeyboard());
    }

    /**
     * 切换中文键盘
     */
    public void switchKeyBoardChina(){
        currentKeyboard=mKeyboardChina;
        setKeyboard(mKeyboardChina.getKeyboard());
    }


    private void initKeyboardView(Context context) {
        Keyboard keyboardUpperLetter = new Keyboard(context, R.xml.upper);
        Keyboard keyboardLetter = new Keyboard(context, R.xml.qwerty);
        Keyboard keyboardNum = new Keyboard(context, R.xml.digit);
        Keyboard keyboardSymbol = new Keyboard(context, R.xml.symbol);
        Keyboard keyboardChina = new Keyboard(context, R.xml.china);

        mKeyboardUpperLetter = new QZKeyboard(keyboardUpperLetter,new LetterOnKeyAdapter((InputMethodService)this.getContext()));
        mKeyboardLetter = new QZKeyboard(keyboardLetter,new LetterOnKeyAdapter((InputMethodService)this.getContext()));
        mKeyboardNum = new QZKeyboard(keyboardNum,new LetterOnKeyAdapter((InputMethodService)this.getContext()));
        mKeyboardSymbol = new QZKeyboard(keyboardSymbol,new LetterOnKeyAdapter((InputMethodService)this.getContext()));
        mKeyboardChina = new QZKeyboard(keyboardChina,new LetterOnKeyAdapter((InputMethodService)this.getContext()));
        //默认显示字母键盘
        switchKeyBoardChina();
        setOnKeyboardActionListener(new QZKeyboardActionListener(this));
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {

            int code = key.codes[0];
            // LogUtil.e("KEY", "Drawing key with code " + code);
            //ABC
            if (code == Keyboard.KEYCODE_SHIFT) {
                drawKeyBackground(R.drawable.keyboard_shift, canvas, key);
            }
            //切换输入法
            if (code == QZKeyCode.CODE_TYPE_SWITCH_INPUT) {
                drawKeyBackground(R.drawable.keyboard_switch, canvas, key);
            }
            //删除
            if (code == QZKeyCode.KEYCODE_DELETE) {
                drawKeyBackground(R.drawable.keyboard_delete, canvas, key);
            }
            //完成 return
            else if (code == Keyboard.KEYCODE_DONE) {
                drawKeyBackground(R.drawable.keyboard_enter, canvas, key);
            }

            // 符号 数字 abc
            else if (code == QZKeyCode.CODE_TYPE_SYMBOL || code == QZKeyCode.CODE_TYPE_QWERTY || code == QZKeyCode.CODE_TYPE_NUM) {
                drawKeyBackground(R.drawable.keyboard_gray, canvas, key);
                drawText(canvas, key);
            }

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent me) {


        float x = me.getX();
        float y = me.getY();
        switch (me.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mDownX = x;
                mDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                setPreviewEnabled(false);
                //滑动距离小于10dp时不隐藏键盘预览 大于10dp时隐藏键盘按键预览
                if (Math.abs(x - mDownX) > QZUtil.dp2px(10) || Math.abs(y - mDownY) > QZUtil.dp2px(10)) {
                    //取消预览
                    setPopupOffset(0, QZUtil.dp2px(0));
                }
                break;
        }


        return super.onTouchEvent(me);

    }

    /**
     * 绘制按键背景
     *
     * @param drawableId
     * @param canvas
     * @param key
     */
    private void drawKeyBackground(int drawableId, Canvas canvas, Keyboard.Key key) {
        Drawable npd = (Drawable) getContext().getResources().getDrawable(drawableId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            npd.setState(drawableState);
        }
        //绘制按键背景  加上 MyUtil.dp2px(4)
        npd.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        npd.draw(canvas);
    }


    /**
     * 绘制文字
     *
     * @param canvas
     * @param key
     */
    private void drawText(Canvas canvas, Keyboard.Key key) {
        Rect bounds = new Rect();
        bounds.set(key.x, key.y, key.x + key.width, key.y + key.height);
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(40);

        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        if (Keyboard.KEYCODE_DONE == key.codes[0]) {
            paint.setColor(Color.WHITE);
        } else {
            paint.setColor(ContextCompat.getColor(MyApp.getInstance(), android.R.color.black));
        }

        if (key.label != null) {
            paint.getTextBounds(key.label.toString(), 0, key.label.toString().length(), bounds);
            canvas.drawText(key.label.toString(), key.x + (key.width / 2), (key.y + key.height / 2) + bounds
                    .height() / 2, paint);
        }
    }


    public QZKeyboard getCurrentKeyboard() {
        return currentKeyboard;
    }

    public void setView(View view) {
        this.view = view;
    }
}
