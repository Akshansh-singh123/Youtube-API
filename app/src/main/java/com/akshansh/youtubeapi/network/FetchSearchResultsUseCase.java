package com.akshansh.youtubeapi.network;

import android.util.Log;

import com.akshansh.youtubeapi.Keys;
import com.akshansh.youtubeapi.common.BaseObservable;
import com.akshansh.youtubeapi.common.utils.InternetConnectionTester;
import com.akshansh.youtubeapi.schemas.searchschema.YoutubeSearchSchema;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchSearchResultsUseCase extends BaseObservable<FetchSearchResultsUseCase.Listener> {
    public interface Listener{
        void OnFetchedVideos(YoutubeSearchSchema schema);
        void OnSearchListFetchFailure(String message);
        void OnNetworkError();
    }

    private final YoutubeApi youtubeApi;
    private final InternetConnectionTester internetConnectionTester;
    private static final String TAG = "Search";

    public FetchSearchResultsUseCase(YoutubeApi youtubeApi, InternetConnectionTester internetConnectionTester) {
        this.youtubeApi = youtubeApi;
        this.internetConnectionTester = internetConnectionTester;
    }

    public void fetchVideos(String searchTerm){
        if(!internetConnectionTester.isConnected()){
            for(Listener listener: getListeners()){
                listener.OnNetworkError();
            }
            return;
        }

        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("part","snippet");
        hashMap.put("key", Keys.API_KEY);
        hashMap.put("q",searchTerm);
        hashMap.put("maxResults",10);
        hashMap.put("type","video");
        hashMap.put("order","relevance");
        fetchVideos(hashMap);
        Log.e(TAG, "fetchVideos: ");
    }

    public void fetchVideos(String searchTerm,String pageToken){
        if(!internetConnectionTester.isConnected()){
            for(Listener listener: getListeners()){
                listener.OnNetworkError();
            }
            return;
        }
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("part","snippet");
        hashMap.put("key", Keys.API_KEY);
        hashMap.put("q",searchTerm);
        hashMap.put("maxResults",10);
        hashMap.put("type","video");
        hashMap.put("order","relevance");
        hashMap.put("pageToken",pageToken);
        fetchVideos(hashMap);
    }

    private void fetchVideos(Map<String,Object> hashMap){
        Call<YoutubeSearchSchema> schemaCall = youtubeApi.getSearchResults(hashMap);
        schemaCall.enqueue(new Callback<YoutubeSearchSchema>() {
            @Override
            public void onResponse(Call<YoutubeSearchSchema> call, Response<YoutubeSearchSchema> response) {
                if(response.isSuccessful()) {
                    YoutubeSearchSchema youtubeSearchSchema = response.body();
                    if(youtubeSearchSchema != null){
                        for(Listener listener: getListeners()){
                            listener.OnFetchedVideos(youtubeSearchSchema);
                        }
                    }else{
                        for(Listener listener: getListeners()){
                            listener.OnSearchListFetchFailure("Failed to fetch search results");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<YoutubeSearchSchema> call, Throwable t) {
                for(Listener listener: getListeners()){
                    listener.OnSearchListFetchFailure(t.getMessage());
                }
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
