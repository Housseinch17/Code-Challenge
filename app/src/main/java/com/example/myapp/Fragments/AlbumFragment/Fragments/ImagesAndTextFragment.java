package com.example.myapp.Fragments.AlbumFragment.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapp.JsonParsing.Album.ALBUMJSON;
import com.example.myapp.JsonParsing.Album.AlbumAdapter;
import com.example.myapp.JsonParsing.Album.Meals;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.AlbumCustomAdapter.ImagesAndTextCustomAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ImagesAndTextFragment extends Fragment {
View view;
ProgressBar imagesAndTextProgressBar;
RecyclerView imagesAndTextRecycler;
    public ImagesAndTextFragment() {
    }

    public static ImagesAndTextFragment newInstance(){
        return new ImagesAndTextFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_images_and_text, container, false);
        imagesAndTextRecycler=view.findViewById(R.id.imagesAndTextRecycler);
        imagesAndTextProgressBar=view.findViewById(R.id.imagesAndTextProgressBar);
        imagesAndTextProgressBar.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        imagesAndTextRecycler.setLayoutManager(layoutManager);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ALBUMJSON albumjson=retrofit.create(ALBUMJSON.class);
        Call<AlbumAdapter> call=albumjson.getJson("");
        call.enqueue(new Callback<AlbumAdapter>() {
            @Override
            public void onResponse(Call<AlbumAdapter> call, Response<AlbumAdapter> response) {
                if(!response.isSuccessful())
                    Toast.makeText(getContext(), "Not successful", Toast.LENGTH_LONG).show();
                else {
                    imagesAndTextProgressBar.setVisibility(View.INVISIBLE);
                    ArrayList<Meals> meals=response.body().getMeals();
                    ImagesAndTextCustomAdapter customAdapter=new ImagesAndTextCustomAdapter(meals);
                    imagesAndTextRecycler.setAdapter(customAdapter);

                }
            }

            @Override
            public void onFailure(Call<AlbumAdapter> call, Throwable t) {
                Toast.makeText(getContext(), "Failure "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}