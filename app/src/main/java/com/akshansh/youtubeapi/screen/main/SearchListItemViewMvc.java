package com.akshansh.youtubeapi.screen.main;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.screen.common.views.ObservableViewMvc;

public interface SearchListItemViewMvc extends ObservableViewMvc<SearchListItemViewMvc.Listener> {
    interface Listener{
        void OnSearchItemClicked(Model model);
    }
    void bindView(Model schemas);
    void bindVideoThumbnail(String url);
    void bindVideoTitle(String title);
    void bindVideoDescription(String description);
    void bindLikesText(String likes);
    void bindViewsText(String views);
}
