package com.input.qz.input.service;

import android.view.View;

public interface OnKeyAdapter {

    void onKey(int primaryCode, int[] keyCodes);

    void setView(View view);
}
