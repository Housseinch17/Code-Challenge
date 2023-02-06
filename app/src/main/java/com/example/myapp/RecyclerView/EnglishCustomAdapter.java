package com.example.myapp.RecyclerView;

import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.JsonParsing.ArabicNews.Article;
import com.example.myapp.R;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EnglishCustomAdapter extends RecyclerView.Adapter<EnglishCustomAdapter.MyViewHolder>{
    ArrayList<Article> articles;

    public EnglishCustomAdapter(ArrayList<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public EnglishCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list= LayoutInflater.from(parent.getContext()).inflate(R.layout.englishnewslist,parent,false);
        return new MyViewHolder(list);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        
        holder.englishName.setText(articles.get(position).getSource().getName());
        holder.englishTitle.setText(articles.get(position).getTitle());
        String date=format(articles.get(position).getPublishedAt());
        holder.englishDate.setText(date);


        holder.englishDescription.setText(articles.get(position).getDescription());
        boolean isVisible=articles.get(position).isVisibilty();
        holder.englishDescription.setVisibility(isVisible? View.VISIBLE : View.GONE);

        if(isVisible)
            holder.englishDrawable.setImageResource(R.drawable.ic_baseline_arrow_upward_24);
        else
            holder.englishDrawable.setImageResource(R.drawable.ic_baseline_arrow_downward_24);

        holder.englishTitle.setMovementMethod(new ScrollingMovementMethod());
        holder.englishDescription.setMovementMethod(new ScrollingMovementMethod());
        Glide.with(holder.itemView.getContext())
                .load(articles.get(position).getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.englishImage);


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView englishName,englishTitle,englishDate,englishDescription;
        ImageView englishImage,englishDrawable;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            englishName=itemView.findViewById(R.id.englishName);
            englishTitle=itemView.findViewById(R.id.englishTitle);
            englishDate=itemView.findViewById(R.id.englishDate);
            englishImage=itemView.findViewById(R.id.englishImage);
            englishDescription=itemView.findViewById(R.id.englishDescription);
            englishDrawable=itemView.findViewById(R.id.englishDrawable);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    articles.get(getAdapterPosition()).setVisibilty(!articles.get(getAdapterPosition()).isVisibilty());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
    public final String format(String date){
        String data="";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            data = Instant.parse(date)
                    .atOffset(ZoneOffset.UTC)
                    .format(
                            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
                    );
        }
        return data;
    }

}
