package com.example.myapp.JsonParsing.EnglishNews;

import com.example.myapp.JsonParsing.ArabicNews.ArabicAdapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ENGLISHJSON {
    @GET("everything")
    Call<EnglishAdapter> getData(@Query("q") String q,@Query("from") String from,@Query("to") String to,
                                @Query("sortBy") String sortBy,@Query("apiKey") String apiKey);
}
