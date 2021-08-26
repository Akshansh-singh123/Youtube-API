package com.akshansh.youtubeapi.screen.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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
    private final SearchView searchView;
    private TextView emptyTextView;

    public MainViewMvcImpl(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        binding = ActivityMainBinding.inflate(inflater,parent,false);
        setRootView(binding.getRoot());
        progressFrame = binding.progressFrame;
        recyclerView = binding.recyclerView;
        searchView = binding.searchView;
        emptyTextView = binding.emptyTextView;
        adapter = new MainAdapter(this,viewMvcFactory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void bindView() {
        recyclerView.setVisibility(View.GONE);
        emptyTextView.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        progressFrame.setVisibility(View.GONE);
        Log.e("TAG", "bindView: ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                for(Listener listener: getListeners()){
                    listener.OnSearchQuery(query);
                }
                hideKeyboard(searchView);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void bindListItems(List<Model> modelList, int pageNumber) {
        adapter.bindList(modelList,pageNumber);
        recyclerView.setVisibility(View.VISIBLE);
        emptyTextView.setVisibility(View.GONE);
        progressFrame.setVisibility(View.GONE);
        searchView.setVisibility(View.VISIBLE);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void clearBinding() {
        binding = null;
    }

    @Override
    public void showProgressFrame() {
        recyclerView.setVisibility(View.GONE);
        emptyTextView.setVisibility(View.GONE);
        progressFrame.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressFrame() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyTextView.setVisibility(View.GONE);
        progressFrame.setVisibility(View.GONE);
        searchView.setVisibility(View.VISIBLE);
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
