package com.akshansh.youtubeapi.common.dependecyinjection.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;


import com.akshansh.youtubeapi.common.ViewMvcFactory;
import com.akshansh.youtubeapi.common.utils.InternetConnectionTester;
import com.akshansh.youtubeapi.screen.common.screensnavigator.ScreensNavigator;
import com.akshansh.youtubeapi.screen.common.toast.ToastHelper;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    public AppCompatActivity getActivity(){
        return activity;
    }

    @Provides
    public Context getContext(AppCompatActivity activity){
        return activity;
    }

    @Provides
    public Resources getResources(AppCompatActivity activity) {
        return activity.getResources();
    }

    @Provides
    public LayoutInflater getLayoutInflater(AppCompatActivity activity){
        return LayoutInflater.from(activity);
    }

    @Provides
    public ToastHelper getToastHelper(AppCompatActivity activity){
        return new ToastHelper(activity);
    }

    @Provides
    public ViewMvcFactory getViewMvcFactory(LayoutInflater inflater){
        return new ViewMvcFactory(inflater);
    }

    @Provides
    public ScreensNavigator getScreensNavigator(AppCompatActivity activity){
        return new ScreensNavigator(activity);
    }

    @Provides
    public InternetConnectionTester getInternetConnectionTester(AppCompatActivity activity){
        return new InternetConnectionTester(activity);
    }
}
