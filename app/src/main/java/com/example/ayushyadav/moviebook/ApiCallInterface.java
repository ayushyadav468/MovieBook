package com.example.ayushyadav.moviebook;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ayushyadav on 25/03/18.
 */

public interface ApiCallInterface {

    @GET("movie/now_playing?api_key=" + Key.apiKey + "&language=en-US&page=1")
    Call<imdbData> getData();

}
