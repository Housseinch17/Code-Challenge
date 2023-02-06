package com.example.myapp.RecyclerView.AlbumCustomAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.JsonParsing.Album.Meals;
import com.example.myapp.R;

import java.util.ArrayList;

public class ImagesOnlyCustomAdapter extends RecyclerView.Adapter<ImagesOnlyCustomAdapter.MyViewHolder> {
    ArrayList<Meals> meals;

    public ImagesOnlyCustomAdapter(ArrayList<Meals> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public ImagesOnlyCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list= LayoutInflater.from(parent.getContext()).inflate(R.layout.imagesonlylist,parent,false);
        return new MyViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(meals.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imagesOnlyImage);
    }



    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagesOnlyImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagesOnlyImage=itemView.findViewById(R.id.imagesOnlyImage);
        }
    }
}
