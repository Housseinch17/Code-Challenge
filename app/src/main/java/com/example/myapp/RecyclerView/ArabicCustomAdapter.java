package com.example.myapp.RecyclerView;

import android.os.Build;
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

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ArabicCustomAdapter extends RecyclerView.Adapter<ArabicCustomAdapter.MyViewHolder> {
    ArrayList<Article> arabicAdapterArrayList;

    public ArabicCustomAdapter(ArrayList<Article> arabicAdapterArrayList) {
        this.arabicAdapterArrayList = arabicAdapterArrayList;
    }

    @NonNull
    @Override
    public ArabicCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list= LayoutInflater.from(parent.getContext()).inflate(R.layout.arabicnewslist,parent,false);
        return new MyViewHolder(list);
    }

    @Override
    public void onBindViewHolder(@NonNull ArabicCustomAdapter.MyViewHolder holder, int position) {
        holder.arabicName.setText(arabicAdapterArrayList.get(position).getSource().getName());
        holder.arabicTitle.setText(arabicAdapterArrayList.get(position).getTitle());
        String dates=format(arabicAdapterArrayList.get(position).getPublishedAt());
        String date=convertNumberEnglishToArabic(dates);
        holder.arabicDate.setText(date);

        holder.arabicDescription.setText(arabicAdapterArrayList.get(position).getDescription());
        boolean isVisible=arabicAdapterArrayList.get(position).isVisibilty();
        holder.arabicDescription.setVisibility(isVisible? View.VISIBLE : View.GONE);

        if(isVisible)
            holder.arabicDrawable.setImageResource(R.drawable.ic_baseline_arrow_upward_24);
        else
            holder.arabicDrawable.setImageResource(R.drawable.ic_baseline_arrow_downward_24);

        Glide.with(holder.itemView.getContext())
                .load(arabicAdapterArrayList.get(position).getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.arabicImage);
    }


    @Override
    public int getItemCount() {
        return arabicAdapterArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView arabicName,arabicTitle,arabicDate,arabicDescription;
        ImageView arabicImage,arabicDrawable;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            arabicName=itemView.findViewById(R.id.arabicName);
            arabicTitle=itemView.findViewById(R.id.arabicTitle);
            arabicDate=itemView.findViewById(R.id.arabicDate);
            arabicImage=itemView.findViewById(R.id.arabicImage);
            arabicDescription=itemView.findViewById(R.id.arabicDescription);
            arabicDrawable=itemView.findViewById(R.id.arabicDrawable);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    arabicAdapterArrayList.get(getAdapterPosition()).setVisibilty(!arabicAdapterArrayList.get(getAdapterPosition()).isVisibilty());
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

    public static String convertNumberEnglishToArabic(String input) {

        String value = "";

        for (char character : input.toCharArray()) {

            String str = "";
            int ascii = (int) character;

            if (ascii >= 48 && ascii <= 57) {
                //english number
                int valueOld = ascii + 1584;
                char valueChar = (char) valueOld;
                str = String.valueOf(valueChar);
            } else {
                //default
                str = String.valueOf(character);
            }

            value += str;
        }
        return value;
    }


}
