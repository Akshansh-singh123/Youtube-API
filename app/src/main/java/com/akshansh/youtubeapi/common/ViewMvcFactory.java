package com.akshansh.youtubeapi.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.akshansh.youtubeapi.screen.main.MainViewMvc;
import com.akshansh.youtubeapi.screen.main.MainViewMvcImpl;
import com.akshansh.youtubeapi.screen.main.listitem.SearchListItemViewMvc;
import com.akshansh.youtubeapi.screen.main.listitem.SearchListItemViewMvcImpl;
import com.akshansh.youtubeapi.screen.main.listitem.SkipListItemViewMvc;
import com.akshansh.youtubeapi.screen.main.listitem.SkipListItemViewMvcImpl;
import com.akshansh.youtubeapi.screen.videoplayer.VideoPlayerViewMvc;
import com.akshansh.youtubeapi.screen.videoplayer.VideoPlayerViewMvcImpl;

public class ViewMvcFactory {
    private final LayoutInflater inflater;

    public ViewMvcFactory(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public VideoPlayerViewMvc getVideoPlayerViewMvc(@Nullable ViewGroup parent){
        return new VideoPlayerViewMvcImpl(inflater,parent);
    }

    public SearchListItemViewMvc getSearchListItemViewMVc(@Nullable ViewGroup parent){
        return new SearchListItemViewMvcImpl(inflater,parent);
    }

    public SkipListItemViewMvc getSkipListItemViewMvc(@Nullable ViewGroup parent){
        return new SkipListItemViewMvcImpl(inflater,parent);
    }

    public MainViewMvc getMainViewMvc(ViewGroup parent) {
        return new MainViewMvcImpl(inflater,parent,this);
    }
}
