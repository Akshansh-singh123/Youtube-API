package com.akshansh.youtubeapi;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import com.akshansh.youtubeapi.common.Constants;
import com.akshansh.youtubeapi.common.CustomApplication;
import com.akshansh.youtubeapi.databinding.ActivityVideoBinding;
import com.akshansh.youtubeapi.network.FetchVideoDescUseCase;
import com.akshansh.youtubeapi.network.YoutubeApi;
import com.akshansh.youtubeapi.schemas.descriptionschema.YoutubeSnippetSchema;
import com.akshansh.youtubeapi.screen.common.YoutubeBaseActivity;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends YoutubeBaseActivity implements YouTubePlayer.OnInitializedListener,
        YouTubePlayer.PlaybackEventListener, YouTubePlayer.PlayerStateChangeListener, FetchVideoDescUseCase.Listener {
    @Inject public FetchVideoDescUseCase fetchVideoDescUseCase;
    private Model model;
    private ActivityVideoBinding binding;
    private YouTubePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInjector().inject(this);
        binding = ActivityVideoBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
        model = (Model) getIntent().getSerializableExtra(Constants.VIDEO_EXTRA);
        binding.player.initialize(Keys.API_KEY,this);
        binding.videoTitle.setText(model.getVideoTitle());
        binding.likesText.setText(model.getVideoLikesCount());
        binding.viewsText.setText(model.getVideoViewCount());
        binding.shareButton.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,Constants.YOUTUBE_URL+model.getVideoId());
            intent.setType("text/plain");
            try {
                startActivity(intent);
            }catch (ActivityNotFoundException e){
                e.printStackTrace();
            }
        });
        fetchVideoDescUseCase.fetchVideoDescription(model.getVideoId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchVideoDescUseCase.registerListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        fetchVideoDescUseCase.unregisterListener(this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        player = youTubePlayer;
        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);
        if(!b){
            youTubePlayer.cueVideo(model.getVideoId());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }

    @Override
    public void OnFetchVideoDescSuccessful(YoutubeSnippetSchema schema) {
        binding.videoDescText.setText(schema.getItems().get(0).getSnippet().getDescription());
    }

    @Override
    public void OnFetchVideoDescFailure(String message) {
        binding.videoDescText.setText(model.getVideoDescription());
    }

    @Override
    public void OnNetworkError() {
        binding.videoDescText.setText(model.getVideoDescription());
    }
}