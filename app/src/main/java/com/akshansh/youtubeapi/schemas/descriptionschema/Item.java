package com.akshansh.youtubeapi.schemas.descriptionschema;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("snippet")
    private Snippet snippet;

    public Item(Snippet snippet) {
        this.snippet = snippet;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}
