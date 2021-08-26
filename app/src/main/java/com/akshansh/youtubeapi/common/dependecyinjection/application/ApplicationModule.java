package com.akshansh.youtubeapi.common.dependecyinjection.application;

import com.akshansh.youtubeapi.common.Constants;
import com.akshansh.youtubeapi.common.CustomApplication;
import com.akshansh.youtubeapi.network.YoutubeApi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private final CustomApplication application;

    public ApplicationModule(CustomApplication application) {
        this.application = application;
    }

    @Provides
    @AppScope
    public ExecutorService getExecutor(){
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Provides
    @AppScope
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    public YoutubeApi getYoutubeApi(Retrofit retrofit){
        return retrofit.create(YoutubeApi.class);
    }
}
