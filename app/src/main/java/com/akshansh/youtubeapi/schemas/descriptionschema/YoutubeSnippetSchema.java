package com.akshansh.youtubeapi.schemas.descriptionschema;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YoutubeSnippetSchema {
    @SerializedName("items")
    private List<Item> items;

    public YoutubeSnippetSchema(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
