package com.akshansh.youtubeapi.schemas.statsschema;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YoutubeStatsSchema {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("pageInfo")
    @Expose
    private PageInfo pageInfo;

    public YoutubeStatsSchema() {
    }

    public YoutubeStatsSchema(String kind, String etag, List<Item> items, PageInfo pageInfo) {
        super();
        this.kind = kind;
        this.etag = etag;
        this.items = items;
        this.pageInfo = pageInfo;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}