package com.example.ayushyadav.moviebook;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ayushyadav on 25/03/18.
 */

public class MoviesList {

    @SerializedName("results")
    public ArrayList<ImdbData> results;

    public MoviesList(ArrayList<ImdbData> results) {
        this.results = results;
    }

    public ArrayList<ImdbData> getResults() {
        return results;
    }

    public void setResults(ArrayList<ImdbData> results) {
        this.results = results;
    }
}
