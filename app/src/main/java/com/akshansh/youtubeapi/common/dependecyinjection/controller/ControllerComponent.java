package com.akshansh.youtubeapi.common.dependecyinjection.controller;

import com.akshansh.youtubeapi.screen.main.MainActivity;

import dagger.Subcomponent;

@ControllerScope
@Subcomponent(modules = {ControllerModule.class})
public interface ControllerComponent {
    void inject(MainActivity mainActivity);
}
