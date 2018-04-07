package com.example.ayushyadav.moviebook;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ayushyadav on 25/03/18.
 */

public class MoviesList {

    @SerializedName("results")
    ArrayList<imdbData.nowShowingData> results;

}
