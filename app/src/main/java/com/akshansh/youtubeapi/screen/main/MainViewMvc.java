package com.akshansh.youtubeapi.screen.main;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.screen.common.views.ObservableViewMvc;

import java.util.List;

public interface MainViewMvc extends ObservableViewMvc<MainViewMvc.Listener> {
    interface Listener{
        void OnSearchItemClicked(Model model);
        void OnNextButtonClicked();
        void OnPreviousButtonClicked();
    }
    void bindListItems(List<Model> modelList, int pageNumber);
    void clearBinding();
    void showProgressFrame();
    void hideProgressFrame();
}
