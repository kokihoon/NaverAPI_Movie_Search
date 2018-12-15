package com.example.kokihoon.naverapi_movie_search.data;

import java.util.ArrayList;

public class MovieList {
    public String lastBuildDate;
    public int total;
    public int start;
    public int display;

    public ArrayList<MovieItem> items = new ArrayList<>();
}
