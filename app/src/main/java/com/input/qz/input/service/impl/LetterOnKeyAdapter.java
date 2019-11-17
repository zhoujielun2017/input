package com.input.qz.input.service.impl;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

import com.example.kamkeyboard.util.StringUtils;
import com.input.qz.input.R;
import com.input.qz.input.service.OnKeyAdapter;
import com.input.qz.input.view.SlideTabView;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;
import java.util.List;

public class LetterOnKeyAdapter implements OnKeyAdapter{

    public static final String TAG ="LetterOnKeyAdapter";
    private InputMethodService inputMethodService;
    private View view;
    private TextView textView;
    private SlideTabView keyboardSpanTab;

    public LetterOnKeyAdapter(InputMethodService inputMethodService) {
        this.inputMethodService = inputMethodService;
    }

    @Deprecated
    public void onKey(int primaryCode, int[] keyCodes){
        InputConnection ic = inputMethodService.getCurrentInputConnection();
//        Log.i(TAG,keyboardSpanTab.get);
        CharSequence item0 = keyboardSpanTab.getTabText(0);

        switch (primaryCode) {

            //删除
            case Keyboard.KEYCODE_DELETE:

                if(null!=item0&&item0.length()>0){
                    keyboardSpanTab.updateTabText(0,delete(item0));
                }
                if(null!=item0&&item0.length()==1){
                    keyboardSpanTab.reset();
                }
                ic.deleteSurroundingText(1, 0);
                break;
            default:
                String inputChar = String.valueOf((char) primaryCode);
                Log.i(TAG,item0+"----"+inputChar);
                if(null!=item0){
                    keyboardSpanTab.updateTabText(0,append(item0,inputChar));
                }else{
                    Log.i(TAG,"addTab");
                    List<String> list = new ArrayList<String>();
                    for (int i = 0; i < 8; i++) {
                        list.add("Item" + (i + 1));
                    }
                    keyboardSpanTab.initTab(list);
                }
                ic.commitText(inputChar, 1);
        }
    }

    private String delete(CharSequence source){
        if(null!=source&&source.length()>0){
            CharSequence dest=source.subSequence(0,source.length()-1);
            return dest.toString();
        }
        return source.toString();
    }

    private String append(CharSequence source,CharSequence append){
        StringBuilder sb = new StringBuilder();
        sb.append(source).append(append);
        return sb.toString();
    }


    public void setView(View view) {
        this.view = view;
        textView=view.findViewById(R.id.keyboardLetterInput);
        keyboardSpanTab=view.findViewById(R.id.keyboardSpanTab);
    }
}
