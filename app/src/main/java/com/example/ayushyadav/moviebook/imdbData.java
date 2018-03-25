package com.example.ayushyadav.moviebook;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ayushyadav on 20/03/18.
 */

public class imdbData {

        @SerializedName("title")
        private String title;
        @SerializedName("release_date")
        private String releasedDate;
        @SerializedName("original_language")
        private String language;
        @SerializedName("poster_path")
        private String poster;

        public imdbData(String title, String releasedDate, String language, String poster) {
            this.title = title;
            this.releasedDate = releasedDate;
            this.language = language;
            this.poster = poster;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getReleasedDate() {
            return releasedDate;
        }

        public void setReleasedDate(String releasedDate) {
            this.releasedDate = releasedDate;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getImage() {
            return poster;
        }

        public void setImage(String poster) {
            this.poster = poster;
        }

}
