package com.katkov.training_starwars.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public final class Movie {

    @SerializedName("title")
    private final String title;
    @SerializedName("director")
    private final String director;
    @SerializedName("opening_crawl")
    private final String openingCrawl;
    @SerializedName("release_date")
    private final String releaseDate;

    public Movie(String title, String director, String openingCrawl, String releaseDate) {
        this.title = title;
        this.director = director;
        this.openingCrawl = openingCrawl;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
            Objects.equals(director, movie.director) &&
            Objects.equals(openingCrawl, movie.openingCrawl) &&
            Objects.equals(releaseDate, movie.releaseDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, director, openingCrawl, releaseDate);
    }

    @Override
    public String toString() {
        return "Movie{" +
            "title='" + title + '\'' +
            ", director='" + director + '\'' +
            ", openingCrawl='" + openingCrawl + '\'' +
            ", releaseDate='" + releaseDate + '\'' +
            '}';
    }
}

