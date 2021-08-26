package com.akshansh.youtubeapi;

import java.io.Serializable;

public class Model implements Serializable {
    private String videoId;
    private String videoTitle;
    private String thumbnailUrl;
    private String videoDescription;
    private String videoViewCount;
    private String videoLikesCount;

    public Model() {
    }

    public Model(String videoId, String videoTitle, String thumbnailUrl, String videoDescription,
                 String videoViewCount, String videoLikesCount) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.thumbnailUrl = thumbnailUrl;
        this.videoDescription = videoDescription;
        this.videoViewCount = videoViewCount;
        this.videoLikesCount = videoLikesCount;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getVideoViewCount() {
        return videoViewCount;
    }

    public void setVideoViewCount(String videoViewCount) {
        this.videoViewCount = videoViewCount;
    }

    public String getVideoLikesCount() {
        return videoLikesCount;
    }

    public void setVideoLikesCount(String videoLikesCount) {
        this.videoLikesCount = videoLikesCount;
    }

    @Override
    public String toString() {
        return "Model{" +
                "videoId='" + videoId + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", videoDescription='" + videoDescription + '\'' +
                ", videoViewCount='" + videoViewCount + '\'' +
                ", videoLikesCount='" + videoLikesCount + '\'' +
                '}';
    }
}
