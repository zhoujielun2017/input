package com.input.qz.input.service.impl;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

import com.input.qz.input.R;
import com.input.qz.input.service.OnKeyAdapter;
import com.input.qz.input.view.SlideTabView;
import com.qmuiteam.qmui.widget.QMUITabSegment;

public class ChinaOnKeyAdapter implements OnKeyAdapter {

    private InputMethodService inputMethodService;
    private View view;
    private TextView textView;
    private SlideTabView keyboardSpanTab;

    public ChinaOnKeyAdapter(InputMethodService inputMethodService) {
        this.inputMethodService = inputMethodService;
    }

    @Deprecated
    public void onKey(int primaryCode, int[] keyCodes){
        InputConnection ic = inputMethodService.getCurrentInputConnection();

        switch (primaryCode) {
            //删除
            case Keyboard.KEYCODE_DELETE:
                CharSequence text = textView.getText();
                if(null!=text&&text.length()>0){
                    CharSequence dest=text.subSequence(0,text.length()-1);
                    textView.setText(dest);
                }
                break;
            default:
                String inputChar = String.valueOf((char) primaryCode);
                textView.append(inputChar);
       }
//        ic.commitText(String.valueOf(inputChar), 1);
    }

    public void setView(View view) {
        this.view = view;
        textView=view.findViewById(R.id.keyboardLetterInput);
        keyboardSpanTab=view.findViewById(R.id.keyboardSpanTab);
    }
}
