package com.akshansh.youtubeapi.screen.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.common.ViewMvcFactory;
import com.akshansh.youtubeapi.databinding.ListItemBinding;
import com.akshansh.youtubeapi.databinding.ListItemSkipLayouBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter implements SearchListItemViewMvc.Listener,
        SkipListItemViewMvc.Listener {
    private static final int VIEW_TYPE_VIDEO = 100;
    private static final int VIEW_TYPE_PAGE_BUTTON = 101;

    public interface Listener{
        void onItemClicked(Model model);
        void onPrevButtonClicked();
        void onNextButtonClicked();
    }
    private List<Model> models = new ArrayList<>();
    private final ViewMvcFactory viewMvcFactory;
    private final Listener listener;
    private int pageNumber = 1;

    public MainAdapter(Listener listener, ViewMvcFactory viewMvcFactory) {
        this.listener = listener;
        this.viewMvcFactory = viewMvcFactory;
    }

    public void bindList(List<Model> models,int pageNumber){
        this.models = new ArrayList<>(models);
        this.pageNumber = pageNumber;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < models.size()){
            return VIEW_TYPE_VIDEO;
        }
        return VIEW_TYPE_PAGE_BUTTON;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_VIDEO) {
            SearchListItemViewMvc viewMvc = viewMvcFactory.getSearchListItemViewMVc(parent);
            return new VideoViewHolder(viewMvc);
        }else{
            SkipListItemViewMvc viewMvc = viewMvcFactory.getSkipListItemViewMvc(parent);
            return new PageButtonsViewHolder(viewMvc);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position < models.size()){
            Model model = models.get(position);
            VideoViewHolder viewHolder = (VideoViewHolder)holder;
            viewHolder.viewMvc.bindView(model);
            viewHolder.viewMvc.bindVideoTitle(model.getVideoTitle());
            viewHolder.viewMvc.bindVideoDescription(model.getVideoDescription());
            viewHolder.viewMvc.bindLikesText(model.getVideoLikesCount());
            viewHolder.viewMvc.bindViewsText(model.getVideoViewCount());
            viewHolder.viewMvc.bindVideoThumbnail(model.getThumbnailUrl());
            viewHolder.viewMvc.registerListener(this);
        }else{
            PageButtonsViewHolder viewHolder = (PageButtonsViewHolder) holder;
            viewHolder.viewMvc.bindView(pageNumber);
            viewHolder.viewMvc.registerListener(this);
        }
    }

    @Override
    public int getItemCount() {
        return models.size()+1;
    }

    @Override
    public void OnSearchItemClicked(Model model) {
        listener.onItemClicked(model);
    }

    @Override
    public void OnPreviousButtonClicked() {
        listener.onPrevButtonClicked();
    }

    @Override
    public void OnNextButtonClicked() {
        listener.onNextButtonClicked();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder{
        private final SearchListItemViewMvc viewMvc;

        public VideoViewHolder(@NonNull SearchListItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            this.viewMvc = viewMvc;
        }
    }

    public static class PageButtonsViewHolder extends RecyclerView.ViewHolder{
        private final SkipListItemViewMvc viewMvc;

        public PageButtonsViewHolder(@NonNull SkipListItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            this.viewMvc = viewMvc;
        }
    }
}
