package com.akshansh.youtubeapi.screen.main;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.common.ViewMvcFactory;
import com.akshansh.youtubeapi.databinding.ActivityMainBinding;
import com.akshansh.youtubeapi.network.FetchSearchResultsUseCase;
import com.akshansh.youtubeapi.network.FetchVideoStatisticsUseCase;
import com.akshansh.youtubeapi.network.YoutubeApi;
import com.akshansh.youtubeapi.schemas.searchschema.YoutubeSearchSchema;
import com.akshansh.youtubeapi.screen.common.BaseActivity;
import com.akshansh.youtubeapi.screen.common.screensnavigator.ScreensNavigator;
import com.akshansh.youtubeapi.screen.common.toast.ToastHelper;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements FetchSearchResultsUseCase.Listener,
        FetchVideoStatisticsUseCase.Listener, MainViewMvc.Listener {
    @Inject public YoutubeApi youtubeApi;
    @Inject public FetchSearchResultsUseCase fetchSearchResultsUseCase;
    @Inject public ToastHelper toastHelper;
    @Inject public FetchVideoStatisticsUseCase fetchVideoStatisticsUseCase;
    @Inject public ScreensNavigator screensNavigator;
    @Inject public ViewMvcFactory viewMvcFactory;
    private MainViewMvc viewMvc;
    private YoutubeSearchSchema youtubeSearchSchema;
    private int pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInjector().inject(this);
        viewMvc = viewMvcFactory.getMainViewMvc(null);
        setContentView(viewMvc.getRootView());
        pageNumber = 1;
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchSearchResultsUseCase.registerListener(this);
        fetchVideoStatisticsUseCase.registerListener(this);
        viewMvc.registerListener(this);
        fetchSearchResultsUseCase.fetchVideos("cricket");
        viewMvc.showProgressFrame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewMvc.clearBinding();
        viewMvc.unregisterListener(this);
        fetchSearchResultsUseCase.unregisterListener(this);
        fetchVideoStatisticsUseCase.unregisterListener(this);
    }

    @Override
    public void OnFetchedVideos(YoutubeSearchSchema schema) {
        if(schema != null){
            youtubeSearchSchema = schema;
            if(youtubeSearchSchema.getPrevPageToken() == null){
                pageNumber = 1;
            }
            fetchVideoStatisticsUseCase.fetchVideoStats(schema);
        }
    }

    @Override
    public void OnSearchListFetchFailure(String message) {
        toastHelper.makeToast(message);
    }

    @Override
    public void OnFetchedStatsSuccessfully(List<Model> models) {
        viewMvc.bindListItems(models,pageNumber);
        viewMvc.hideProgressFrame();
    }

    @Override
    public void OnStatsFetchFailure(String message) {
        toastHelper.makeToast(message);
        viewMvc.hideProgressFrame();
    }

    @Override
    public void OnNetworkError() {
        toastHelper.makeToast("Check your internet connection");
    }

    @Override
    public void OnSearchItemClicked(Model model) {
        screensNavigator.toVideoActivity(model);
    }

    @Override
    public void OnNextButtonClicked() {
        if(youtubeSearchSchema != null){
            viewMvc.showProgressFrame();
            pageNumber += 1;
            if(youtubeSearchSchema.getNextPageToken() != null){
                fetchSearchResultsUseCase.fetchVideos("cricket",
                        youtubeSearchSchema.getNextPageToken());
            }
        }
    }

    @Override
    public void OnPreviousButtonClicked() {
        if(youtubeSearchSchema != null){
            viewMvc.showProgressFrame();
            pageNumber -= 1;
            if(youtubeSearchSchema.getPrevPageToken() != null){
                fetchSearchResultsUseCase.fetchVideos("cricket",
                        youtubeSearchSchema.getPrevPageToken());
            }
        }
    }
}