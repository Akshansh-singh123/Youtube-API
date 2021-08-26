package com.akshansh.youtubeapi.screen.videoplayer;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.akshansh.youtubeapi.Keys;
import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.common.Constants;
import com.akshansh.youtubeapi.databinding.ActivityVideoBinding;
import com.akshansh.youtubeapi.schemas.descriptionschema.YoutubeSnippetSchema;
import com.akshansh.youtubeapi.screen.common.views.BaseObservableViewMvc;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPlayerViewMvcImpl extends BaseObservableViewMvc<VideoPlayerViewMvc.Listener>
        implements VideoPlayerViewMvc, YouTubePlayer.OnInitializedListener,
        YouTubePlayer.PlayerStateChangeListener, YouTubePlayer.PlaybackEventListener {
    private ActivityVideoBinding binding;
    private final YouTubePlayerView playerView;
    private final ImageButton shareButton;
    private final TextView titleTextView;
    private final TextView descriptionTextView;
    private final TextView likesTextView;
    private final TextView viewsTextView;
    private YouTubePlayer player;
    private Model model;

    public VideoPlayerViewMvcImpl(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
        binding = ActivityVideoBinding.inflate(inflater,parent,false);
        setRootView(binding.getRoot());
        playerView = binding.player;
        shareButton = binding.shareButton;
        titleTextView = binding.videoTitle;
        descriptionTextView = binding.videoDescText;
        likesTextView = binding.likesText;
        viewsTextView = binding.viewsText;
    }

    @Override
    public void bindView(Model model) {
        this.model = model;
        playerView.initialize(Keys.API_KEY,this);
        titleTextView.setText(model.getVideoTitle());
        likesTextView.setText(model.getVideoLikesCount());
        viewsTextView.setText(model.getVideoViewCount());
        shareButton.setOnClickListener(v->{
            for(Listener listener: getListeners()){
                listener.OnVideoShareButtonClicked(Constants.YOUTUBE_URL+ model.getVideoId());
            }
        });
    }

    @Override
    public void bindDescription(String description) {
        descriptionTextView.setText(description);
    }

    @Override
    public void clearBinding() {
        binding = null;
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
        for(Listener listener: getListeners()){
            listener.OnInitializationFailure(youTubeInitializationResult.name());
        }
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
        for(Listener listener: getListeners()){
            listener.OnVideoError(errorReason.name());
        }
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
}
