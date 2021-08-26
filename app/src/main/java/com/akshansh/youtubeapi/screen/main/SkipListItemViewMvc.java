package com.akshansh.youtubeapi.screen.main;

import com.akshansh.youtubeapi.screen.common.views.ObservableViewMvc;

public interface SkipListItemViewMvc extends ObservableViewMvc<SkipListItemViewMvc.Listener> {
    interface Listener{
        void OnPreviousButtonClicked();
        void OnNextButtonClicked();
    }
    void bindView(int pageNumber);
}
