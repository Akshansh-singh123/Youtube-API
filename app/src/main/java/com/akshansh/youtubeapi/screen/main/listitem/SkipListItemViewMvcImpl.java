package com.akshansh.youtubeapi.screen.main.listitem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.akshansh.youtubeapi.databinding.ListItemSkipLayoutBinding;
import com.akshansh.youtubeapi.screen.common.views.BaseObservableViewMvc;

public class SkipListItemViewMvcImpl extends BaseObservableViewMvc<SkipListItemViewMvc.Listener>
        implements SkipListItemViewMvc {
    private ListItemSkipLayoutBinding binding;
    private final Button nextButton;
    private final Button prevButton;
    private final TextView pageNumberTextView;

    public SkipListItemViewMvcImpl(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
        binding = ListItemSkipLayoutBinding.inflate(inflater,parent,false);
        setRootView(binding.getRoot());
        nextButton = binding.nextButton;
        prevButton = binding.prevButton;
        pageNumberTextView = binding.pageText;
    }

    @Override
    public void bindView(int pageNumber) {
        prevButton.setEnabled(pageNumber != 1);
        nextButton.setEnabled(pageNumber != 10);
        pageNumberTextView.setText(String.valueOf(pageNumber));
        nextButton.setOnClickListener(v -> {
            for(Listener listener: getListeners()){
                listener.OnNextButtonClicked();
            }
        });
        prevButton.setOnClickListener(v -> {
            for(Listener listener: getListeners()){
                listener.OnPreviousButtonClicked();
            }
        });
    }
}
