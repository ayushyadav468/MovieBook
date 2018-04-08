package com.example.ayushyadav.moviebook;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ayushyadav on 25/03/18.
 */

public class MoviesList {

    @SerializedName("results")
    public ArrayList<imdbData.nowShowingData> results;

    public MoviesList(ArrayList<imdbData.nowShowingData> results) {
        this.results = results;
    }

    public ArrayList<imdbData.nowShowingData> getResults() {
        return results;
    }

    public void setResults(ArrayList<imdbData.nowShowingData> results) {
        this.results = results;
    }
}
