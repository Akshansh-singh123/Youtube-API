package com.akshansh.youtubeapi.screen;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.VideoAdapter;
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

public class MainActivity extends BaseActivity implements VideoAdapter.Listener,
        FetchSearchResultsUseCase.Listener, FetchVideoStatisticsUseCase.Listener {
    @Inject public YoutubeApi youtubeApi;
    @Inject public FetchSearchResultsUseCase fetchSearchResultsUseCase;
    @Inject public ToastHelper toastHelper;
    @Inject public FetchVideoStatisticsUseCase fetchVideoStatisticsUseCase;
    @Inject public ScreensNavigator screensNavigator;
    private YoutubeSearchSchema youtubeSearchSchema;
    private ActivityMainBinding binding;
    private VideoAdapter adapter;
    private int pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInjector().inject(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
        adapter = new VideoAdapter(getLayoutInflater(),this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        pageNumber = 1;
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchSearchResultsUseCase.registerListener(this);
        fetchVideoStatisticsUseCase.registerListener(this);
        binding.progressFrame.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.GONE);
        fetchSearchResultsUseCase.fetchVideos("cricket");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        fetchSearchResultsUseCase.unregisterListener(this);
        fetchVideoStatisticsUseCase.unregisterListener(this);
    }

    @Override
    public void onItemClicked(Model model) {
        screensNavigator.toVideoActivity(model);
    }

    @Override
    public void onPrevButtonClicked() {
        if(youtubeSearchSchema != null){
            binding.progressFrame.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
            pageNumber -= 1;
            if(youtubeSearchSchema.getPrevPageToken() != null){
                fetchSearchResultsUseCase.fetchVideos("cricket",
                        youtubeSearchSchema.getPrevPageToken());
            }
        }
    }

    @Override
    public void onNextButtonClicked() {
        if(youtubeSearchSchema != null){
            binding.progressFrame.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
            pageNumber += 1;
            if(youtubeSearchSchema.getNextPageToken() != null){
                fetchSearchResultsUseCase.fetchVideos("cricket",
                        youtubeSearchSchema.getNextPageToken());
            }
        }
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
    public void OnFetchFailure(String message) {
        toastHelper.makeToast(message);
    }

    @Override
    public void OnFetchedStatsSuccessfully(List<Model> models) {
        adapter.bindList(models,pageNumber);
        binding.recyclerView.setVisibility(View.VISIBLE);
        binding.progressFrame.setVisibility(View.GONE);
        binding.recyclerView.scrollToPosition(0);
    }

    @Override
    public void OnStatsFetchFailure(String message) {
        toastHelper.makeToast(message);
        binding.progressFrame.setVisibility(View.GONE);
    }

    @Override
    public void OnNetworkError() {
        toastHelper.makeToast("Check your internet connection");
    }
}