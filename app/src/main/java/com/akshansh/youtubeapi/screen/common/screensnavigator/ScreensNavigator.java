package com.akshansh.youtubeapi.screen.common.screensnavigator;

import android.content.Context;
import android.content.Intent;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.VideoActivity;
import com.akshansh.youtubeapi.common.Constants;

public class ScreensNavigator {
    private final Context context;

    public ScreensNavigator(Context context) {
        this.context = context;
    }

    public void toVideoActivity(Model model) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(Constants.VIDEO_EXTRA,model);
        context.startActivity(intent);
    }
}
