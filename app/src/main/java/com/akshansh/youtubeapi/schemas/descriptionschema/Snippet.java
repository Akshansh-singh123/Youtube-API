package com.akshansh.youtubeapi.schemas.descriptionschema;

import com.google.gson.annotations.SerializedName;

public class Snippet {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;

    public Snippet(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
