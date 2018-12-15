package com.example.kokihoon.naverapi_movie_search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView titleView;
    public TextView pubDateView;
    public TextView actorView;
    public TextView directorView;
    public ImageView imageView;
    public RatingBar ratingBar;

    public ViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.imageView);
        titleView = (TextView)itemView.findViewById(R.id.titleView);
        pubDateView = (TextView)itemView.findViewById(R.id.pubDateView1);
        actorView = (TextView)itemView.findViewById(R.id.actorView1);
        directorView = (TextView)itemView.findViewById(R.id.directorView1);
        ratingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);

    }
}
