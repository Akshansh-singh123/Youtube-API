package com.akshansh.youtubeapi.screen.common.toast;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {
    private final Context context;

    public ToastHelper(Context context) {
        this.context = context;
    }

    public void makeToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
