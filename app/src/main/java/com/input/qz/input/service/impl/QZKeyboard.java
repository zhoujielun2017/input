package com.input.qz.input.service.impl;

import android.inputmethodservice.Keyboard;

import com.input.qz.input.service.OnKeyAdapter;

public class QZKeyboard {

    private Keyboard keyboard;
    private OnKeyAdapter onKeyAdapter;

    public QZKeyboard(Keyboard keyboard, OnKeyAdapter onKeyAdapter) {
        this.keyboard = keyboard;
        this.onKeyAdapter = onKeyAdapter;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public OnKeyAdapter getOnKeyAdapter() {
        return onKeyAdapter;
    }

    public void setOnKeyAdapter(OnKeyAdapter onKeyAdapter) {
        this.onKeyAdapter = onKeyAdapter;
    }
}
