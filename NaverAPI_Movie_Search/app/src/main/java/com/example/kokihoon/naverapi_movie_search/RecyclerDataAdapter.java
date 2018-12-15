package com.example.kokihoon.naverapi_movie_search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.kokihoon.naverapi_movie_search.data.MovieItem;

import java.util.ArrayList;

public class RecyclerDataAdapter extends RecyclerView.Adapter<ViewHolder>{

    private ArrayList<MovieItem> movies;

    RecyclerDataAdapter(ArrayList<MovieItem> items1) {
        this.movies = items1;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View itemView = layoutInflater.inflate(R.layout.movie_item, parent, false);


        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MovieItem movieItem = movies.get(position);

        ImageView imageView = holder.imageView;

        RatingBar ratingBar = holder.ratingBar;
        ratingBar.setIsIndicator(true);
        ratingBar.setRating(Float.parseFloat(movieItem.userRating)/2);

        TextView titleView = holder.titleView;
        titleView.setText(movieItem.title);

        TextView pubDateView = holder.pubDateView;
        pubDateView.setText(movieItem.pubDate);

        TextView actorView = holder.actorView;
        actorView.setText(movieItem.actor);

        TextView directorView = holder.directorView;
        directorView.setText(movieItem.director);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}
