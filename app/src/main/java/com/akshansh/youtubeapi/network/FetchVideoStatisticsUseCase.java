package com.akshansh.youtubeapi.network;

import com.akshansh.youtubeapi.Keys;
import com.akshansh.youtubeapi.Model;
import com.akshansh.youtubeapi.common.BaseObservable;
import com.akshansh.youtubeapi.common.utils.InternetConnectionTester;
import com.akshansh.youtubeapi.schemas.searchschema.Item;
import com.akshansh.youtubeapi.schemas.searchschema.YoutubeSearchSchema;
import com.akshansh.youtubeapi.schemas.statsschema.YoutubeStatsSchema;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchVideoStatisticsUseCase extends BaseObservable<FetchVideoStatisticsUseCase.Listener> {
    public interface Listener{
        void OnFetchedStatsSuccessfully(List<Model> models);
        void OnStatsFetchFailure(String message);
        void OnNetworkError();
    }
    private final YoutubeApi youtubeApi;
    private final InternetConnectionTester connectionTester;
    private List<Model> models;

    public FetchVideoStatisticsUseCase(YoutubeApi youtubeApi, InternetConnectionTester connectionTester) {
        this.youtubeApi = youtubeApi;
        this.connectionTester = connectionTester;
    }

    public void fetchVideoStats(YoutubeSearchSchema schema){
        if(!connectionTester.isConnected()){
            for(Listener listener: getListeners()){
                listener.OnNetworkError();
            }
        }
        models = new ArrayList<>();
        if(schema.getItems() != null) {
            for (Item item : schema.getItems()) {
                Model model = new Model();
                model.setThumbnailUrl(item.getSnippet().getThumbnails().getMedium().getUrl());
                model.setVideoId(item.getId().getVideoId());
                model.setVideoTitle(item.getSnippet().getTitle());
                model.setVideoDescription(item.getSnippet().getDescription());
                fetchStatForVideo(schema,model);
            }
        }else{
            for(Listener listener: getListeners()){
                listener.OnStatsFetchFailure("Something went wrong");
            }
        }
    }

    private void fetchStatForVideo(YoutubeSearchSchema youtubeSearchSchema,Model model) {
        Call<YoutubeStatsSchema> schemaCall = youtubeApi
                .getVideoStats("statistics", Keys.API_KEY,model.getVideoId());
        schemaCall.enqueue(new Callback<YoutubeStatsSchema>() {
            @Override
            public void onResponse(Call<YoutubeStatsSchema> call, Response<YoutubeStatsSchema> response) {
                if(response.isSuccessful()){
                    try{
                        YoutubeStatsSchema schema = response.body();
                        model.setVideoViewCount(schema.getItems().get(0).getStatistics().getViewCount());
                        model.setVideoLikesCount(schema.getItems().get(0).getStatistics().getLikeCount());
                        models.add(model);
                        if(models.size() == youtubeSearchSchema.getItems().size()){
                            for(Listener listener: getListeners()){
                                listener.OnFetchedStatsSuccessfully(models);
                            }
                        }
                    }catch (Exception e){
                        for(Listener listener: getListeners()){
                            listener.OnStatsFetchFailure("Something went wrong");
                        }
                    }
                }else{
                    for(Listener listener: getListeners()){
                        listener.OnStatsFetchFailure(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<YoutubeStatsSchema> call, Throwable t) {
                for(Listener listener: getListeners()){
                    listener.OnStatsFetchFailure(t.getMessage());
                }
            }
        });
    }
}
