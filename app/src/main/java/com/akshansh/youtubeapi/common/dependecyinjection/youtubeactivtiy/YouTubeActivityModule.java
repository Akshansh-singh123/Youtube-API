package com.akshansh.youtubeapi.common.dependecyinjection.youtubeactivtiy;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.akshansh.youtubeapi.common.ViewMvcFactory;
import com.akshansh.youtubeapi.common.utils.InternetConnectionTester;
import com.akshansh.youtubeapi.network.FetchSearchResultsUseCase;
import com.akshansh.youtubeapi.network.FetchVideoDescUseCase;
import com.akshansh.youtubeapi.network.FetchVideoStatisticsUseCase;
import com.akshansh.youtubeapi.network.YoutubeApi;
import com.akshansh.youtubeapi.screen.common.YoutubeBaseActivity;
import com.akshansh.youtubeapi.screen.common.screensnavigator.ScreensNavigator;
import com.akshansh.youtubeapi.screen.common.toast.ToastHelper;
import com.google.android.youtube.player.YouTubeBaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class YouTubeActivityModule {
    private final YouTubeBaseActivity activity;

    public YouTubeActivityModule(YoutubeBaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public YouTubeBaseActivity getActivity(){
        return activity;
    }

    @Provides
    public Context getContext(YouTubeBaseActivity activity){
        return activity;
    }

    @Provides
    public Resources getResources(YouTubeBaseActivity activity) {
        return activity.getResources();
    }

    @Provides
    public LayoutInflater getLayoutInflater(YouTubeBaseActivity activity){
        return LayoutInflater.from(activity);
    }

    @Provides
    public ToastHelper getToastHelper(YouTubeBaseActivity activity){
        return new ToastHelper(activity);
    }

    @Provides
    public ViewMvcFactory getViewMvcFactory(LayoutInflater inflater){
        return new ViewMvcFactory(inflater);
    }

    @Provides
    public ScreensNavigator getScreensNavigator(YouTubeBaseActivity activity){
        return new ScreensNavigator(activity);
    }

    @Provides
    public InternetConnectionTester getInternetConnectionTester(YouTubeBaseActivity activity){
        return new InternetConnectionTester(activity);
    }

    @Provides
    public FetchSearchResultsUseCase getFetchSearchResultUseCase(InternetConnectionTester
                                                                         connectionTester,
                                                                 YoutubeApi youtubeApi){
        return new FetchSearchResultsUseCase(youtubeApi,connectionTester);
    }

    @Provides
    public FetchVideoStatisticsUseCase getFetchStatisticsUseCase(InternetConnectionTester
                                                                         connectionTester,
                                                                 YoutubeApi youtubeApi){
        return new FetchVideoStatisticsUseCase(youtubeApi, connectionTester);
    }

    @Provides
    public FetchVideoDescUseCase getFetchVideoDescUseCase(InternetConnectionTester
                                                                  connectionTester,
                                                          YoutubeApi youtubeApi){
        return new FetchVideoDescUseCase(youtubeApi, connectionTester);
    }
}
