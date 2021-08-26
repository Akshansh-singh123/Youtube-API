package com.akshansh.youtubeapi.common.dependecyinjection.controller;

import android.os.Handler;
import android.os.Looper;

import com.akshansh.youtubeapi.common.utils.InternetConnectionTester;
import com.akshansh.youtubeapi.network.FetchSearchResultsUseCase;
import com.akshansh.youtubeapi.network.FetchVideoDescUseCase;
import com.akshansh.youtubeapi.network.FetchVideoStatisticsUseCase;
import com.akshansh.youtubeapi.network.YoutubeApi;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {
    @Provides
    @ControllerScope
    public Handler getUiThread(){
        return new Handler(Looper.getMainLooper());
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
