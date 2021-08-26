package com.akshansh.youtubeapi.common.dependecyinjection.application;

import com.akshansh.youtubeapi.common.dependecyinjection.activity.ActivityComponent;
import com.akshansh.youtubeapi.common.dependecyinjection.activity.ActivityModule;
import com.akshansh.youtubeapi.common.dependecyinjection.youtubeactivtiy.YouTubeActivityComponent;
import com.akshansh.youtubeapi.common.dependecyinjection.youtubeactivtiy.YouTubeActivityModule;

import dagger.Component;

@AppScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    ActivityComponent newActivityComponent(ActivityModule activityModule);
    YouTubeActivityComponent newYouTubeActivityComponent(YouTubeActivityModule youTubeActivityModule);
}
