package com.input.qz.input.service.impl;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

import com.input.qz.input.MyApp;
import com.input.qz.input.bean.English;
import com.input.qz.input.constants.QZKeyCode;
import com.input.qz.input.dao.EnglishDao;
import com.input.qz.input.constants.InputContainer;
import com.input.qz.input.service.OnKeyAdapter;
import com.input.qz.input.view.SlideTabView;

import java.util.ArrayList;
import java.util.List;

public class LetterOnKeyAdapter implements OnKeyAdapter{

    public static final String TAG ="LetterOnKeyAdapter";
    private InputMethodService inputMethodService;
    private View view;
    private TextView textView;
    private SlideTabView keyboardSpanTab;
    private EnglishDao englishDao;
    private StringBuilder currentWord;
    public LetterOnKeyAdapter(InputMethodService inputMethodService) {
        this.inputMethodService = inputMethodService;
        englishDao = MyApp.getInstance().getDaoSession().getEnglishDao();

    }

    @Deprecated
    public void onKey(int primaryCode, int[] keyCodes){
        InputConnection ic = inputMethodService.getCurrentInputConnection();
//        Log.i(TAG,keyboardSpanTab.get);
        textView=InputContainer.getInstance().getKeyboarTextView();
        keyboardSpanTab = InputContainer.getInstance().getKeyboardSpanTab();
        currentWord=InputContainer.getInstance().getCurrentWord();
        CharSequence item0 = keyboardSpanTab.getTabText(0);

        switch (primaryCode) {
            //空格
            case QZKeyCode.CODE_SPACE:
                currentWord.delete( 0, currentWord.length());
                resetTab();
                ic.commitText(String.valueOf((char) primaryCode), 1);
                break;
            //删除
            case Keyboard.KEYCODE_DELETE:
                if(currentWord.length()>0){
                    currentWord.deleteCharAt(currentWord.length()-1);
                }
                resetTab();
                ic.deleteSurroundingText(1, 0);
                break;
            default:
                String inputChar = String.valueOf((char) primaryCode);
                currentWord.append(inputChar);
                resetTab();
                Log.i(TAG,"---"+currentWord+"---");
                ic.commitText(inputChar, 1);
        }
    }

    private void resetTab(){
        if(currentWord.length()<=0){
            keyboardSpanTab.reset();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(currentWord).append("%");
        List<English> list = englishDao.queryBuilder()
                .where(EnglishDao.Properties.Word.like(sb.toString())).limit(30).list();
        List<String> items = new ArrayList<String>();
        if(list.size()>0&&!list.get(0).getWord().equals(currentWord.toString())){
            items.add(currentWord.toString());
        }
        for (English english : list) {
            items.add(english.getWord());
        }
        keyboardSpanTab.initTab(items);
    }

}
