package com.input.qz.input.service.impl;

import android.inputmethodservice.InputMethodService;
import android.util.Log;
import android.view.inputmethod.InputConnection;

import com.input.qz.input.constants.InputContainer;
import com.input.qz.input.view.OnTabSelectedListener;
import com.input.qz.input.view.SlideTabView;

public class LetterOnTabSelected implements OnTabSelectedListener {

    public static final String TAG = "LetterOnTabSelected";
    private InputMethodService inputMethodService;

    public LetterOnTabSelected(InputMethodService inputMethodService) {
        this.inputMethodService = inputMethodService;
    }

    @Override
    public void onTabSelected(int index) {
        SlideTabView keyboardSpanTab = InputContainer.getInstance().getKeyboardSpanTab();
        InputConnection ic = inputMethodService.getCurrentInputConnection();
        CharSequence tabText = keyboardSpanTab.getTabText(index);
        String currentWord = InputContainer.getInstance().getCurrentWord().toString();
        Log.i(TAG,currentWord);
        ic.deleteSurroundingText(currentWord.length(),0);
        ic.commitText(tabText, 1);
        InputContainer.getInstance().clearCurrentWord();
        InputContainer.getInstance().getKeyboardSpanTab().reset();
    }

    @Override
    public void onTabUnselected(int index) {

    }

    @Override
    public void onTabReselected(int index) {

    }

    @Override
    public void onDoubleTap(int index) {

    }
}
