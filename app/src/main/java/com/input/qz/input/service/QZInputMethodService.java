package com.input.qz.input.service;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.TextView;

import com.input.qz.input.R;
import com.input.qz.input.constants.InputContainer;
import com.input.qz.input.service.impl.ChinaOnTabSelected;
import com.input.qz.input.service.impl.LetterOnTabSelected;
import com.input.qz.input.view.OnTabSelectedListener;
import com.input.qz.input.view.SlideTabView;


public class QZInputMethodService extends InputMethodService {

    private static final String TAG = "IMEServiceSample";

    InputMethodManager mInputMethodManager;

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate -----------");
        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    /**
     *
     */
    @Override
    public void onInitializeInterface() {

    }

    /**
     *
     */
    @Override
    public View onCreateInputView() {
        Log.i(TAG, "onCreateInputView -----------");
        View view = getLayoutInflater().
                inflate(R.layout.keyboard_global, null);
        final SlideTabView keyboardSpanTab = view.findViewById(R.id.keyboardSpanTab);
        TextView keyboardLetterInput = view.findViewById(R.id.keyboardLetterInput);
        final QZKeyboardView keyboardView = view.findViewById(R.id.keyboardView);

//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < 8; i++) {
//            list.add("Item" + (i + 1));
//        }
//        keyboardSpanTab.initTab(list);
        final InputConnection ic = this.getCurrentInputConnection();
        final ChinaOnTabSelected chinaOnTabSelected = new ChinaOnTabSelected(this);
        final LetterOnTabSelected letterOnTabSelected = new LetterOnTabSelected(this);
        keyboardSpanTab.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                if(keyboardView.isChinaKeyboard()){
                    chinaOnTabSelected.onTabSelected(index);
                }else{
                    letterOnTabSelected.onTabSelected(index);
                }

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
        });
        InputContainer.getInstance().setKeyboardSpanTab(keyboardSpanTab);
        InputContainer.getInstance().setKeyboarTextView(keyboardLetterInput);
        return view;
    }

    /**
     *
     */
    @Override
    public View onCreateCandidatesView() {
        Log.i(TAG, "onCreateCandidatesView -----------");
        return null;
    }

    /**
     *
     */
    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        Log.i(TAG, "onStartInput -----------");
    }

    /**
     *
     */
    @Override
    public void onFinishInput() {
        Log.i(TAG, "onFinishInput -----------");
    }

    /**
     *
     */
    @Override
    public void onStartInputView(EditorInfo attribute, boolean restarting) {
        Log.i(TAG, "onStartInputView -----------"+restarting);
    }

    /**
     *
     */
    @Override
    public void onCurrentInputMethodSubtypeChanged(InputMethodSubtype subtype) {
        Log.i(TAG, "onCurrentInputMethodSubtypeChanged -----------");
    }

    /**
     *
     */
    @Override
    public void onUpdateSelection(int oldSelStart, int oldSelEnd,
                                  int newSelStart, int newSelEnd,
                                  int candidatesStart, int candidatesEnd) {
        Log.i(TAG, "onUpdateSelection -----------"+oldSelStart);
    }

    /**
     *
     */
    @Override
    public void onDisplayCompletions(CompletionInfo[] completions) {
        Log.i(TAG, "onDisplayCompletions -----------");
    }

    /**
     *
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyDown -----------"+keyCode);
        return false;
    }

    /**
     *
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyUp -----------"+keyCode);
        return false;
    }

    public Context getContext(){
        return this;
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
//            QDTabSegmentScrollableModeFragment.ContentPage page = QDTabSegmentScrollableModeFragment.ContentPage.getPage(position);
            View view =  new TextView(getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view, params);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            View view = (View) object;
            Object page = view.getTag();
            return POSITION_NONE;
        }
    };
}
