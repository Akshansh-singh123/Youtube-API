package com.akshansh.youtubeapi.schemas.statsschema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageInfo {

    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("resultsPerPage")
    @Expose
    private Integer resultsPerPage;

    public PageInfo() {
    }

    public PageInfo(Integer totalResults, Integer resultsPerPage) {
        super();
        this.totalResults = totalResults;
        this.resultsPerPage = resultsPerPage;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

}