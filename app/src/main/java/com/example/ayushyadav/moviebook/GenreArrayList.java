package com.example.ayushyadav.moviebook;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ayushyadav on 08/04/18.
 */

public class GenreArrayList {

    @SerializedName("genres")
    public ArrayList<Genres> allGenre;

    public GenreArrayList(ArrayList<Genres> allGenre) {
        this.allGenre = allGenre;
    }

    public ArrayList<Genres> getAllGenre() {
        return allGenre;
    }

    public void setAllGenre(ArrayList<Genres> allGenre) {
        this.allGenre = allGenre;
    }
}
