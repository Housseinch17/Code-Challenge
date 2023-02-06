package com.example.myapp.RecyclerView.AlbumCustomAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.JsonParsing.Album.Meals;
import com.example.myapp.R;

import java.util.ArrayList;

public class ImagesAndTextCustomAdapter extends RecyclerView.Adapter<ImagesAndTextCustomAdapter.MyViewHolder> {
    ArrayList<Meals> meals;

    public ImagesAndTextCustomAdapter(ArrayList<Meals> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public ImagesAndTextCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list= LayoutInflater.from(parent.getContext()).inflate(R.layout.imageandtextlist,parent,false);
        return new MyViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAndTextCustomAdapter.MyViewHolder holder, int position) {
        holder.imagesAndTextText.setText(meals.get(position).getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(meals.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imagesAndTextImage);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagesAndTextImage;
        TextView imagesAndTextText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagesAndTextImage=itemView.findViewById(R.id.imagesAndTextImage);
            imagesAndTextText=itemView.findViewById(R.id.imagesAndTextText);
        }
    }
}
