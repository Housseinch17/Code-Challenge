package com.example.myapp.JsonParsing.ArabicNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ARABICJSON {
    @GET("top-headlines")
    Call<ArabicAdapter> getJson(@Query("country") String country,@Query("apiKey") String apikey);
}
