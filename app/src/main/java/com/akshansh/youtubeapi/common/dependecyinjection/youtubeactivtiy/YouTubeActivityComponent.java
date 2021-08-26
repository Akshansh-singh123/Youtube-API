package com.akshansh.youtubeapi.common.dependecyinjection.youtubeactivtiy;


import com.akshansh.youtubeapi.VideoActivity;

import dagger.Subcomponent;

@YouTubeActivityScope
@Subcomponent(modules = {YouTubeActivityModule.class})
public interface YouTubeActivityComponent {
    void inject(VideoActivity videoActivity);
}
