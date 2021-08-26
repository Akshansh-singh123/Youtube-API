package com.akshansh.youtubeapi.common.dependecyinjection.activity;

import com.akshansh.youtubeapi.common.dependecyinjection.controller.ControllerComponent;
import com.akshansh.youtubeapi.common.dependecyinjection.controller.ControllerModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    ControllerComponent newControllerComponent(ControllerModule controllerModule);
}
