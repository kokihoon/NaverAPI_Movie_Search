package com.example.kokihoon.naverapi_movie_search.data;

public class MovieItem {

    public String title;
    public String link;
    public String image;
    public String subtitle;
    public String pubDate;
    public String director;
    public String actor;
    public String userRating;

    public MovieItem(String link, String image, String subtitle, String title, String pubDate, String director, String actor, String userRating) {
        this.link = link;
        this.image = image;
        this.subtitle = subtitle;
        this.title = title;
        this.pubDate = pubDate;
        this.director = director;
        this.actor = actor;
        this.userRating = userRating;
    }
}
