package com.akshansh.youtubeapi.network;

import com.akshansh.youtubeapi.schemas.descriptionschema.YoutubeSnippetSchema;
import com.akshansh.youtubeapi.schemas.searchschema.YoutubeSearchSchema;
import com.akshansh.youtubeapi.schemas.statsschema.YoutubeStatsSchema;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface YoutubeApi {
    @GET("videos")
    Call<YoutubeStatsSchema> getVideoStats(@Query("part") String part,
                                           @Query("key") String key,
                                           @Query("id") String videoId);

    @GET("videos")
    Call<YoutubeSnippetSchema> getVideoFulTitleAndDesc(@Query("part") String part,
                                                       @Query("key") String key,
                                                       @Query("id") String videoId);

    @GET("search")
    Call<YoutubeSearchSchema> getSearchResults(@QueryMap Map<String,Object> parameters);
}
