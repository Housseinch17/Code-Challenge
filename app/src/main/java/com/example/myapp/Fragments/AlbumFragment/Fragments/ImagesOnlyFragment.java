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
import com.example.myapp.RecyclerView.AlbumCustomAdapter.ImagesOnlyCustomAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ImagesOnlyFragment extends Fragment {
View view;
RecyclerView imagesOnlyRecycler;
ProgressBar imagesOnlyProgressBar;

    public ImagesOnlyFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new ImagesOnlyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_images_only, container, false);

        imagesOnlyRecycler=view.findViewById(R.id.imagesOnlyRecycler);

        imagesOnlyProgressBar=view.findViewById(R.id.imagesOnlyProgressBar);
        imagesOnlyProgressBar.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),3,RecyclerView.VERTICAL,false);
        imagesOnlyRecycler.setLayoutManager(layoutManager);

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
                    imagesOnlyProgressBar.setVisibility(View.VISIBLE);
                    ArrayList<Meals> meals=response.body().getMeals();
                    ImagesOnlyCustomAdapter customAdapter=new ImagesOnlyCustomAdapter(meals);
                    imagesOnlyRecycler.setAdapter(customAdapter);

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