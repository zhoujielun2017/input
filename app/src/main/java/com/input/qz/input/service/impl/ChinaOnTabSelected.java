package com.input.qz.input.service.impl;

import android.inputmethodservice.InputMethodService;
import android.util.Log;
import android.view.inputmethod.InputConnection;

import com.input.qz.input.constants.InputContainer;
import com.input.qz.input.view.OnTabSelectedListener;
import com.input.qz.input.view.SlideTabView;

public class ChinaOnTabSelected implements OnTabSelectedListener {

    private InputMethodService inputMethodService;

    public ChinaOnTabSelected(InputMethodService inputMethodService) {
        this.inputMethodService = inputMethodService;
    }

    @Override
    public void onTabSelected(int index) {
        SlideTabView keyboardSpanTab = InputContainer.getInstance().getKeyboardSpanTab();
        InputConnection ic = inputMethodService.getCurrentInputConnection();
        CharSequence tabText = keyboardSpanTab.getTabText(index);
        ic.commitText(tabText, 1);
        InputContainer.getInstance().resetView();
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
