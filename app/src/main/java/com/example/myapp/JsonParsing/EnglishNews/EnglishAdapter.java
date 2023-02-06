package com.example.myapp.JsonParsing.EnglishNews;

import com.example.myapp.JsonParsing.ArabicNews.Article;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EnglishAdapter {
@SerializedName("articles")
    ArrayList<Article> articles;

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

}
