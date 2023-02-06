package com.example.myapp.JsonParsing.Album;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ALBUMJSON {
    @GET("search.php")
    Call<AlbumAdapter> getJson(@Query("s") String name);
}
