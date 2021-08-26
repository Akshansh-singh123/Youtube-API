package com.akshansh.youtubeapi.screen.common.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.content.res.AppCompatResources;

public abstract class BaseViewMvc implements ViewMvc{
    private View rootView;

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    protected Context getContext(){
        return rootView.getContext();
    }

    protected String getString(int stringId){
        return getContext().getString(stringId);
    }

    protected int getColor(int resId){
        return getContext().getColor(resId);
    }

    protected Drawable getDrawable(int resId){
        return AppCompatResources.getDrawable(getContext(),resId);
    }

    protected void hideKeyboard(View view){
        if(view.requestFocus()){
            InputMethodManager imm = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showKeyboard(View view){
        if(view.requestFocus()){
            InputMethodManager imm = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    @Override
    public View getRootView(){
        return rootView;
    }
}
