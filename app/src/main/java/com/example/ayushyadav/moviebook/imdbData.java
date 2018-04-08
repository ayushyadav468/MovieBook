package com.example.ayushyadav.moviebook;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ayushyadav on 20/03/18.
 */

public class imdbData {

    public static class nowShowingData {
        @SerializedName("title")
        private String title;
        @SerializedName("genre_ids")
        private List<Integer> genre;
        @SerializedName("vote_average")
        private String stars;
        @SerializedName("backdrop_path")
        private String poster;

        public nowShowingData(String title, List<Integer> genre, String stars, String poster) {
            this.title = title;
            this.genre = genre;
            this.stars = stars;
            this.poster = poster;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Integer> getGenre() {
            return genre;
        }

        public void setGenre(List<Integer> genre) {
            this.genre = genre;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }
    }

    public class upcomingData{

    }

    public class topRatedData{

    }

}
