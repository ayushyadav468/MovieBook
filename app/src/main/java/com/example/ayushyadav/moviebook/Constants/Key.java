package com.example.ayushyadav.moviebook.Constants;

/**
 * Created by ayushyadav on 19/03/18.
 */

public class Key {

    final public static String apiKey = "3c31e95c56cb0c3ab16e391b160ea636";
    final public static String baseURL = "https://api.themoviedb.org/3/";

    final public static String nowPlayingMoviesURL = "movie/now_playing?api_key=" + apiKey + "&language=en-US&page=1";
    final public static String topRatedMoviesURL = "movie/top_rated?api_key=" + apiKey + "&language=en-US&page=1";
    final public static String upComingMoviesURL = "movie/upcoming?api_key=" + apiKey + "&language=en-US&page=1";
    final public static String popularMoviesURL = "movie/popular?api_key=" + apiKey + "&language=en-US&page=1";

    final public static String posterSizeURL = "http://image.tmdb.org/t/p/w780/";

    final public static String genreURL = "genre/movie/list?api_key=" + apiKey + "&language=en-US";

}