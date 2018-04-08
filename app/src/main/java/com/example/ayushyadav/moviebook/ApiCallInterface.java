package com.example.ayushyadav.moviebook;

import com.example.ayushyadav.moviebook.Constants.Key;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ayushyadav on 25/03/18.
 */

public interface ApiCallInterface {

    @GET(Key.upCommingMoviesURL)
    Call<MoviesList> getData();

    @GET(Key.genreURL)
    Call<GenreArrayList> allGenreCall();

}
