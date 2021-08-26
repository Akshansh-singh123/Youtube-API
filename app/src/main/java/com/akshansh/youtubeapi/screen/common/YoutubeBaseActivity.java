package com.akshansh.youtubeapi.screen.common;

import com.akshansh.youtubeapi.common.CustomApplication;
import com.akshansh.youtubeapi.common.dependecyinjection.youtubeactivtiy.YouTubeActivityComponent;
import com.akshansh.youtubeapi.common.dependecyinjection.youtubeactivtiy.YouTubeActivityModule;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class YoutubeBaseActivity extends YouTubeBaseActivity {
    private YouTubeActivityComponent injector;

    public YouTubeActivityComponent getInjector(){
        if(injector == null){
            injector = ((CustomApplication)getApplication())
                    .getApplicationComponent()
                    .newYouTubeActivityComponent(new YouTubeActivityModule(this));
        }
        return injector;
    }
}
