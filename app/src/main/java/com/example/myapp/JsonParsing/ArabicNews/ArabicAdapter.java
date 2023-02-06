package com.example.myapp.JsonParsing.ArabicNews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArabicAdapter {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    ArrayList<Article> article;

    public ArabicAdapter(String status, Integer totalResults, ArrayList<Article> article) {
        this.status = status;
        this.totalResults = totalResults;
        this.article = article;
    }

    public ArabicAdapter() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Article> getArticle() {
        return article;
    }

    public void setArticle(ArrayList<Article> article) {
        this.article = article;
    }
}
