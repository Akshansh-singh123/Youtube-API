package com.akshansh.youtubeapi.screen.videoplayer;

import android.os.Bundle;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.common.Constants;
import com.akshansh.youtubeapi.common.ViewMvcFactory;
import com.akshansh.youtubeapi.network.FetchVideoDescUseCase;
import com.akshansh.youtubeapi.schemas.descriptionschema.YoutubeSnippetSchema;
import com.akshansh.youtubeapi.screen.common.YoutubeBaseActivity;
import com.akshansh.youtubeapi.screen.common.screensnavigator.ScreensNavigator;
import com.akshansh.youtubeapi.screen.common.toast.ToastHelper;

import javax.inject.Inject;

public class VideoPlayerActivity extends YoutubeBaseActivity implements
        FetchVideoDescUseCase.Listener, VideoPlayerViewMvc.Listener {
    @Inject public FetchVideoDescUseCase fetchVideoDescUseCase;
    @Inject public ToastHelper toastHelper;
    @Inject public ViewMvcFactory viewMvcFactory;
    @Inject public ScreensNavigator screensNavigator;
    private VideoPlayerViewMvc viewMvc;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInjector().inject(this);
        viewMvc = viewMvcFactory.getVideoPlayerViewMvc(null);
        setContentView(viewMvc.getRootView());
        model = (Model) getIntent().getSerializableExtra(Constants.VIDEO_EXTRA);
        fetchVideoDescUseCase.fetchVideoDescription(model.getVideoId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchVideoDescUseCase.registerListener(this);
        viewMvc.registerListener(this);
        viewMvc.bindView(model);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewMvc.clearBinding();
        viewMvc.unregisterListener(this);
        fetchVideoDescUseCase.unregisterListener(this);
    }

    @Override
    public void OnFetchVideoDescSuccessful(YoutubeSnippetSchema schema) {
        viewMvc.bindDescription(schema.getItems().get(0).getSnippet().getDescription());
    }

    @Override
    public void OnFetchVideoDescFailure(String message) {
        viewMvc.bindDescription(model.getVideoDescription());
    }

    @Override
    public void OnNetworkError() {
        viewMvc.bindDescription(model.getVideoDescription());
    }

    @Override
    public void OnVideoShareButtonClicked(String link) {
        screensNavigator.toShare(link);
    }

    @Override
    public void OnInitializationFailure(String message) {
        toastHelper.makeToast(message);
    }

    @Override
    public void OnVideoError(String message) {
        toastHelper.makeToast(message);
    }
}