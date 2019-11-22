package com.input.qz.input.constants;

import android.widget.TextView;

import com.input.qz.input.view.SlideTabView;

/**
 * View的容器,随时可以获取到view
 */
public class InputContainer {
    private static final InputContainer ourInstance = new InputContainer();

    //当前输入的内容View
    private TextView keyboarTextView;
    //键盘上面tab
    private SlideTabView keyboardSpanTab;
    //当前输入的内容
    private StringBuilder currentWord = new StringBuilder();

    public static InputContainer getInstance() {
        return ourInstance;
    }

    /**
     * 重置界面
     */
    public void resetView(){
        currentWord.delete(0,currentWord.length());
        keyboarTextView.setText("");
        keyboardSpanTab.reset();
    }

    private InputContainer() {
    }

    public void clearCurrentWord(){
        currentWord.delete(0,currentWord.length());
    }

    public TextView getKeyboarTextView() {
        return keyboarTextView;
    }

    public void setKeyboarTextView(TextView keyboarTextView) {
        this.keyboarTextView = keyboarTextView;
    }

    public SlideTabView getKeyboardSpanTab() {
        return keyboardSpanTab;
    }

    public void setKeyboardSpanTab(SlideTabView keyboardSpanTab) {
        this.keyboardSpanTab = keyboardSpanTab;
    }

    public StringBuilder getCurrentWord() {
        return currentWord;
    }
}
