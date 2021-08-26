package com.akshansh.youtubeapi.screen.main.listitem;

import com.akshansh.youtubeapi.screen.common.views.ObservableViewMvc;

public interface SkipListItemViewMvc extends ObservableViewMvc<SkipListItemViewMvc.Listener> {
    interface Listener{
        void OnPreviousButtonClicked();
        void OnNextButtonClicked();
    }
    void bindView(int pageNumber);
}
