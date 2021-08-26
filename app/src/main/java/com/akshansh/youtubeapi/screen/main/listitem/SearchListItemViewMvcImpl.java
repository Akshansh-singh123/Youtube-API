package com.akshansh.youtubeapi.screen.main.listitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.databinding.ListItemBinding;
import com.akshansh.youtubeapi.screen.common.views.BaseObservableViewMvc;
import com.squareup.picasso.Picasso;

public class SearchListItemViewMvcImpl extends BaseObservableViewMvc<SearchListItemViewMvc.Listener>
        implements SearchListItemViewMvc {
    private ListItemBinding binding;
    private final TextView titleTextView;
    private final TextView descriptionTextView;
    private final TextView likesTextView;
    private final TextView viewsTextView;
    private final ImageView thumbnailImageView;
    private final View container;

    public SearchListItemViewMvcImpl(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
        binding = ListItemBinding.inflate(inflater,parent,false);
        setRootView(binding.getRoot());
        titleTextView = binding.videoTitleText;
        descriptionTextView = binding.videoDescText;
        likesTextView = binding.likesText;
        viewsTextView = binding.viewsText;
        thumbnailImageView = binding.videoThumbnail;
        container = binding.getRoot();
    }

    @Override
    public void bindView(Model schemas) {
        container.setOnClickListener(v->{
            for(Listener listener: getListeners()){
                listener.OnSearchItemClicked(schemas);
            }
        });
    }

    @Override
    public void bindVideoThumbnail(String url) {
        Picasso.get().load(url).into(thumbnailImageView);
    }

    @Override
    public void bindVideoTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void bindVideoDescription(String description) {
        descriptionTextView.setText(description);
    }

    @Override
    public void bindLikesText(String likes) {
        likesTextView.setText(likes);
    }

    @Override
    public void bindViewsText(String views) {
        viewsTextView.setText(views);
    }
}
