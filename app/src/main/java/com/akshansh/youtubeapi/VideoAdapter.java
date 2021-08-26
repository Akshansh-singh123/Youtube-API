package com.akshansh.youtubeapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akshansh.youtubeapi.databinding.ListItemBinding;
import com.akshansh.youtubeapi.databinding.ListItemSkipLayouBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_VIDEO = 100;
    private static final int VIEW_TYPE_PAGE_BUTTON = 101;

    public interface Listener{
        void onItemClicked(Model model);
        void onPrevButtonClicked();
        void onNextButtonClicked();
    }
    private List<Model> models = new ArrayList<>();
    private final LayoutInflater inflater;
    private final Listener listener;
    private int pageNumber = 1;

    public VideoAdapter(LayoutInflater inflater, Listener listener) {
        this.inflater = inflater;
        this.listener = listener;
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
            ListItemBinding binding = ListItemBinding.inflate(inflater, parent, false);
            return new VideoViewHolder(binding);
        }else{
            ListItemSkipLayouBinding binding = ListItemSkipLayouBinding
                    .inflate(inflater,parent,false);
            return new PageButtonsViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position < models.size()){
            Model model = models.get(position);
            VideoViewHolder viewHolder = (VideoViewHolder)holder;
            viewHolder.binding.videoTitleText.setText(model.getVideoTitle());
            viewHolder.binding.videoDescText.setText(model.getVideoDescription());
            viewHolder.binding.likesText.setText(model.getVideoLikesCount());
            viewHolder.binding.viewsText.setText(model.getVideoViewCount());
            Picasso.get().load(model.getThumbnailUrl()).into(viewHolder.binding.videoThumbnail);
            viewHolder.binding.getRoot().setOnClickListener(v-> listener.onItemClicked(model));
        }else{
            PageButtonsViewHolder viewHolder = (PageButtonsViewHolder) holder;
            viewHolder.binding.prevButton.setEnabled(pageNumber != 1);
            viewHolder.binding.nextButton.setEnabled(pageNumber != 10);
            viewHolder.binding.nextButton.setOnClickListener(v -> listener.onNextButtonClicked());
            viewHolder.binding.prevButton.setOnClickListener(v -> listener.onPrevButtonClicked());
            viewHolder.binding.pageText.setText(String.valueOf(pageNumber));
        }
    }

    @Override
    public int getItemCount() {
        return models.size()+1;
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder{
        private final ListItemBinding binding;

        public VideoViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class PageButtonsViewHolder extends RecyclerView.ViewHolder{
        private final ListItemSkipLayouBinding binding;

        public PageButtonsViewHolder(@NonNull ListItemSkipLayouBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
