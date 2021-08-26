package com.akshansh.youtubeapi.screen.videoplayer;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.screen.common.views.ObservableViewMvc;

public interface VideoPlayerViewMvc extends ObservableViewMvc<VideoPlayerViewMvc.Listener> {
    interface Listener{
        void OnVideoShareButtonClicked(String link);
        void OnInitializationFailure(String message);
        void OnVideoError(String message);
    }
    void bindView(Model model);
    void bindDescription(String description);
    void clearBinding();
}
