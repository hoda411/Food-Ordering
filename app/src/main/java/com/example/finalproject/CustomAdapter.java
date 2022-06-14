package com.example.finalproject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList food_id, food_title, food_des, food_price;

    CustomAdapter(Activity activity, Context context, ArrayList food_id, ArrayList food_title, ArrayList food_des,
                  ArrayList food_price){
        this.activity = activity;
        this.context = context;
        this.food_id = food_id;
        this.food_title = food_title;
        this.food_des = food_des;
        this.food_price = food_price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.food_id_txt.setText(String.valueOf(food_id.get(position)));
        holder.food_title_txt.setText(String.valueOf(food_title.get(position)));
        holder.food_des_txt.setText(String.valueOf(food_des.get(position)));
        holder.food_price_txt.setText(String.valueOf(food_price.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Mainupdate.class);
                intent.putExtra("id", String.valueOf(food_id.get(position)));
                intent.putExtra("title", String.valueOf(food_title.get(position)));
                intent.putExtra("des", String.valueOf(food_des.get(position)));
                intent.putExtra("price", String.valueOf(food_price.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return food_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView food_id_txt, food_title_txt, food_des_txt, food_price_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            food_id_txt = itemView.findViewById(R.id.food_id_txt);
            food_title_txt = itemView.findViewById(R.id.food_title_txt);
            food_des_txt = itemView.findViewById(R.id.food_des_txt);
            food_price_txt = itemView.findViewById(R.id.food_price_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}