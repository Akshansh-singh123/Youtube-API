package com.akshansh.youtubeapi.screen.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.common.ViewMvcFactory;
import com.akshansh.youtubeapi.databinding.ActivityMainBinding;
import com.akshansh.youtubeapi.screen.common.views.BaseObservableViewMvc;

import java.util.List;

public class MainViewMvcImpl extends BaseObservableViewMvc<MainViewMvc.Listener> implements MainViewMvc, MainAdapter.Listener {
    private ActivityMainBinding binding;
    private final View progressFrame;
    private final RecyclerView recyclerView;
    private final MainAdapter adapter;

    public MainViewMvcImpl(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        binding = ActivityMainBinding.inflate(inflater,parent,false);
        setRootView(binding.getRoot());
        progressFrame = binding.progressFrame;
        recyclerView = binding.recyclerView;
        adapter = new MainAdapter(this,viewMvcFactory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void bindListItems(List<Model> modelList, int pageNumber) {
        adapter.bindList(modelList,pageNumber);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void clearBinding() {
        binding = null;
    }

    @Override
    public void showProgressFrame() {
        progressFrame.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressFrame() {
        progressFrame.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClicked(Model model) {
        for(Listener listener: getListeners()){
            listener.OnSearchItemClicked(model);
        }
    }

    @Override
    public void onPrevButtonClicked() {
        for(Listener listener: getListeners()){
            listener.OnPreviousButtonClicked();
        }
    }

    @Override
    public void onNextButtonClicked() {
        for(Listener listener: getListeners()){
            listener.OnNextButtonClicked();
        }
    }
}
