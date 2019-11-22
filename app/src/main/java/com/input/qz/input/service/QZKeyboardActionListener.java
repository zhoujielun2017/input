package com.input.qz.input.service;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.input.qz.input.constants.InputContainer;
import com.input.qz.input.constants.QZKeyCode;
import com.input.qz.input.service.impl.ChinaOnKeyAdapter;
import com.input.qz.input.service.impl.QZKeyboard;

public class QZKeyboardActionListener implements KeyboardView.OnKeyboardActionListener {

    private QZKeyboardView keyboardView;
    //是否是大写字母
    private boolean mIsUpper=false;

    public QZKeyboardActionListener(QZKeyboardView keyboardView) {
        this.keyboardView = keyboardView;
    }

    @Override
    public void onPress(int primaryCode) {

        //设置某些按键不显示预览的view
        keyboardView.setPreviewEnabled(false);
//            if (primaryCode == Keyboard.KEYCODE_SHIFT || primaryCode == Keyboard.KEYCODE_DELETE  //

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        //键盘服务
        QZInputMethodService service = (QZInputMethodService) keyboardView.getContext();
        //当前输入的连接
        InputConnection ic = service.getCurrentInputConnection();
        QZKeyboard qzKeyboard = keyboardView.getCurrentKeyboard();
        switch (primaryCode) {
            //完成
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;

            // 大小写切换
            case Keyboard.KEYCODE_SHIFT:
                mIsUpper = !mIsUpper;
                keyboardView.switchKeyBoardLetter(mIsUpper);
                break;

            // 数字键盘切换
            case QZKeyCode.CODE_TYPE_NUM:
                keyboardView.switchKeyBoardNum();
                break;

            // 字母键盘切换
            case QZKeyCode.CODE_TYPE_QWERTY:
                keyboardView.switchKeyBoardLetter(mIsUpper);
                break;

            // 符号键盘切换
            case QZKeyCode.CODE_TYPE_SYMBOL:
                keyboardView.switchKeyBoardSymbol();
                break;

            // 中文键盘切换
            case QZKeyCode.CODE_TYPE_CHINA:
            case QZKeyCode.CODE_TYPE_SWITCH_EN_ZH:
                keyboardView.switchKeyBoardChina();
                InputContainer.getInstance().resetView();
                break;
            // 中文切换英文键盘
            case QZKeyCode.CODE_TYPE_SWITCH_ZH_EN:
                keyboardView.switchKeyBoardLetter(false);
                break;
            //settings
            case QZKeyCode.CODE_SETTING:
                Toast.makeText(service, "Settings==", Toast.LENGTH_SHORT).show();
                break;

            //切换输入法
            case QZKeyCode.CODE_TYPE_SWITCH_INPUT:
                ((InputMethodManager) service.getSystemService(Context.INPUT_METHOD_SERVICE)).showInputMethodPicker();
                break;

            //一般文本
            default:
                qzKeyboard.getOnKeyAdapter().onKey(primaryCode,keyCodes);



        }

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
