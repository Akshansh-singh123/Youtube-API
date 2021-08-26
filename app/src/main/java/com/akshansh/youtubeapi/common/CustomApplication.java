package com.akshansh.youtubeapi.common;

import android.app.Application;

import com.akshansh.youtubeapi.common.dependecyinjection.application.ApplicationComponent;
import com.akshansh.youtubeapi.common.dependecyinjection.application.ApplicationModule;
import com.akshansh.youtubeapi.common.dependecyinjection.application.DaggerApplicationComponent;

public class CustomApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                                .builder()
                                .applicationModule(new ApplicationModule(this))
                                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
