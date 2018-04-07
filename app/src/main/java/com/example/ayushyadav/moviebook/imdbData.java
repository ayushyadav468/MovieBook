package com.example.ayushyadav.moviebook;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ayushyadav on 20/03/18.
 */

public class imdbData {

    class nowShowingData {
        @SerializedName("title")
        private String title;
        @SerializedName("genre_ids")
        private List<Genres> genre;
        @SerializedName("vote_average")
        private String stars;
        @SerializedName("poster_path")
        private String poster;

        public nowShowingData(String title, List<Genres> genre, String stars, String poster) {
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

        public List<Genres> getGenre() {
            return genre;
        }

        public void setGenre(List<Genres> genre) {
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

    class upcomingData{

    }

    class topRatedData{

    }

}
