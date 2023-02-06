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

import com.example.myapp.JsonParsing.ArabicNews.ARABICJSON;
import com.example.myapp.JsonParsing.ArabicNews.ArabicAdapter;
import com.example.myapp.JsonParsing.ArabicNews.Article;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.ArabicCustomAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArabicNewsFragment extends Fragment {
View view;
ProgressBar arabicProgressBar;
RecyclerView arabicRecycler;

public ArabicNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate(R.layout.fragment_arabic_news2, container, false);
       arabicProgressBar=view.findViewById(R.id.arabicProgressBar);
       arabicProgressBar.setVisibility(View.VISIBLE);
       arabicRecycler=view.findViewById(R.id.arabicRecycler);

        RecyclerView.LayoutManager layoutManager1=new LinearLayoutManager(view.getContext());
        arabicRecycler.setLayoutManager(layoutManager1);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ARABICJSON arabicjson=retrofit.create(ARABICJSON.class);
       Call<ArabicAdapter> call=arabicjson.getJson("ae","8f1cd39cd8264b9189aede91cb211786");
      call.enqueue(new Callback<ArabicAdapter>() {
          @Override
          public void onResponse(Call<ArabicAdapter> call, Response<ArabicAdapter> response) {
              if(!response.isSuccessful())
                  Toast.makeText(getContext(), "Not successful", Toast.LENGTH_LONG).show();
              else{
                  arabicProgressBar.setVisibility(View.INVISIBLE);
                  ArrayList<Article> articles=response.body().getArticle();
                  ArabicCustomAdapter arabicCustomAdapter=new ArabicCustomAdapter(articles);
                  arabicRecycler.setAdapter(arabicCustomAdapter);
              }
          }

          @Override
          public void onFailure(Call<ArabicAdapter> call, Throwable t) {
              Toast.makeText(getContext(), "Failure "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
          }
      });


        return view;
    }
}