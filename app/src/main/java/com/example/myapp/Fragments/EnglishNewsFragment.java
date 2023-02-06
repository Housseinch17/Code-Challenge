package com.example.myapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapp.JsonParsing.ArabicNews.ArabicAdapter;
import com.example.myapp.JsonParsing.ArabicNews.Article;
import com.example.myapp.JsonParsing.EnglishNews.ENGLISHJSON;
import com.example.myapp.JsonParsing.EnglishNews.EnglishAdapter;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.EnglishCustomAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EnglishNewsFragment extends Fragment {
View view;
ProgressBar englishProgressBar;
RecyclerView englishRecycler;
    public EnglishNewsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_english_news2, container, false);
        englishRecycler=view.findViewById(R.id.englishRecycler);
        englishProgressBar=view.findViewById(R.id.englishProgressBar);
        englishProgressBar.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        englishRecycler.setLayoutManager(layoutManager);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ENGLISHJSON englishjson=retrofit.create(ENGLISHJSON.class);
        Call<EnglishAdapter> call=englishjson.getData("apple","2023-01-31","2023-01-31","popularity","8f1cd39cd8264b9189aede91cb211786");
        call.enqueue(new Callback<EnglishAdapter>() {
            @Override
            public void onResponse(Call<EnglishAdapter> call, Response<EnglishAdapter> response) {
                if(!response.isSuccessful())
                    Toast.makeText(getContext(), "Not successfull", Toast.LENGTH_LONG).show();
                else{
                    englishProgressBar.setVisibility(View.INVISIBLE);
                    ArrayList<Article> articles=response.body().getArticles();
                    EnglishCustomAdapter englishCustomAdapter=new EnglishCustomAdapter(articles);
                    englishRecycler.setAdapter(englishCustomAdapter);
                }
            }

            @Override
            public void onFailure(Call<EnglishAdapter> call, Throwable t) {
                Toast.makeText(getContext(), "Failure "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }
}