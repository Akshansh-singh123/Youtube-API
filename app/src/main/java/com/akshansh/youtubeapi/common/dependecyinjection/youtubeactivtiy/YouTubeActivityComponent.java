package com.akshansh.youtubeapi.common.dependecyinjection.youtubeactivtiy;


import com.akshansh.youtubeapi.screen.videoplayer.VideoPlayerActivity;

import dagger.Subcomponent;

@YouTubeActivityScope
@Subcomponent(modules = {YouTubeActivityModule.class})
public interface YouTubeActivityComponent {
    void inject(VideoPlayerActivity videoPlayerActivity);
}
