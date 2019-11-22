package com.input.qz.input.service.impl;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

import com.input.qz.input.MyApp;
import com.input.qz.input.R;
import com.input.qz.input.bean.China;
import com.input.qz.input.bean.English;
import com.input.qz.input.constants.InputContainer;
import com.input.qz.input.dao.ChinaDao;
import com.input.qz.input.dao.EnglishDao;
import com.input.qz.input.service.OnKeyAdapter;
import com.input.qz.input.view.SlideTabView;

import java.util.ArrayList;
import java.util.List;

public class ChinaOnKeyAdapter implements OnKeyAdapter {

    public static final String TAG="ChinaOnKeyAdapter";
    private InputMethodService inputMethodService;
    private View view;
    private TextView textView;
    private SlideTabView keyboardSpanTab;
    private StringBuilder currentWord;
    private ChinaDao chinaDao;

    public ChinaOnKeyAdapter(InputMethodService inputMethodService) {
        this.inputMethodService = inputMethodService;
        currentWord=InputContainer.getInstance().getCurrentWord();
        chinaDao = MyApp.getInstance().getDaoSession().getChinaDao();
    }

    @Deprecated
    public void onKey(int primaryCode, int[] keyCodes){
        InputConnection ic = inputMethodService.getCurrentInputConnection();
        textView=InputContainer.getInstance().getKeyboarTextView();
        keyboardSpanTab=InputContainer.getInstance().getKeyboardSpanTab();
        switch (primaryCode) {
            //删除
            case Keyboard.KEYCODE_DELETE:
                if(currentWord.length()>0){
                    currentWord.deleteCharAt(currentWord.length()-1);
                    textView.setText(currentWord.toString());
                    resetTab();
                }else{
                    ic.deleteSurroundingText(1, 0);
                }
                break;
            default:
                String inputChar = String.valueOf((char) primaryCode);
                append(inputChar);
                resetTab();
                Log.i(TAG,"---"+currentWord+"---");
       }
//        ic.commitText(String.valueOf(inputChar), 1);
    }



    private void append(String inputChar){
        textView.append(inputChar);
        currentWord.append(inputChar);
    }

    private void resetTab(){
        if(currentWord.length()<=0){
            keyboardSpanTab.reset();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(currentWord).append("%");
        List<China> list = chinaDao.queryBuilder()
                .where(ChinaDao.Properties.Pinyin.like(sb.toString())).limit(30).list();
        List<String> items = new ArrayList<String>();
        for (China china : list) {
            items.add(china.getWord());
        }
        keyboardSpanTab.initTab(items);
    }


}
