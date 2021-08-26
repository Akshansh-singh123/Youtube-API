package com.akshansh.youtubeapi.screen.common.screensnavigator;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.screen.videoplayer.VideoPlayerActivity;
import com.akshansh.youtubeapi.common.Constants;

public class ScreensNavigator {
    private final Context context;

    public ScreensNavigator(Context context) {
        this.context = context;
    }

    public void toVideoActivity(Model model) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra(Constants.VIDEO_EXTRA,model);
        context.startActivity(intent);
    }

    public void toShare(String link) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,link);
        intent.setType("text/plain");
        try {
            context.startActivity(intent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }
}
