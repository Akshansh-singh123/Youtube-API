package com.akshansh.youtubeapi.network;

import com.akshansh.youtubeapi.Keys;
import com.akshansh.youtubeapi.common.BaseObservable;
import com.akshansh.youtubeapi.common.utils.InternetConnectionTester;
import com.akshansh.youtubeapi.schemas.descriptionschema.YoutubeSnippetSchema;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchVideoDescUseCase extends BaseObservable<FetchVideoDescUseCase.Listener> {
    public interface Listener{
        void OnFetchVideoDescSuccessful(YoutubeSnippetSchema schema);
        void OnFetchVideoDescFailure(String message);
        void OnNetworkError();
    }

    private final YoutubeApi youtubeApi;
    private final InternetConnectionTester connectionTester;

    public FetchVideoDescUseCase(YoutubeApi youtubeApi, InternetConnectionTester connectionTester) {
        this.youtubeApi = youtubeApi;
        this.connectionTester = connectionTester;
    }

    public void fetchVideoDescription(String videoId){
        if(!connectionTester.isConnected()){
            for(Listener listener: getListeners()){
                listener.OnNetworkError();
            }
        }

        Call<YoutubeSnippetSchema> schemaCall = youtubeApi
                .getVideoFulTitleAndDesc("snippet", Keys.API_KEY,videoId);
        schemaCall.enqueue(new Callback<YoutubeSnippetSchema>() {
            @Override
            public void onResponse(Call<YoutubeSnippetSchema> call, Response<YoutubeSnippetSchema> response) {
                if(response.isSuccessful()){
                    YoutubeSnippetSchema schema = response.body();
                    for(Listener listener: getListeners()){
                        listener.OnFetchVideoDescSuccessful(schema);
                    }
                }else{
                    for(Listener listener: getListeners()){
                        listener.OnFetchVideoDescFailure(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<YoutubeSnippetSchema> call, Throwable t) {
                for(Listener listener: getListeners()){
                    listener.OnFetchVideoDescFailure(t.getMessage());
                }
            }
        });
    }
}
