package com.akshansh.youtubeapi.common.dependecyinjection.controller;

import android.app.Activity;

import com.akshansh.youtubeapi.VideoActivity;
import com.akshansh.youtubeapi.screen.MainActivity;

import dagger.Subcomponent;

@ControllerScope
@Subcomponent(modules = {ControllerModule.class})
public interface ControllerComponent {
    void inject(MainActivity mainActivity);
}
