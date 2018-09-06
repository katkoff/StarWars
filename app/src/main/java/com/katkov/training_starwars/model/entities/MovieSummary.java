package com.katkov.training_starwars.model.entities;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public final class MovieSummary {

    @SerializedName("url")
    private final String url;
    @SerializedName("episode_id")
    private final int episode;
    @SerializedName("title")
    private final String name;
    @SerializedName("opening_crawl")
    private final String description;
    @SerializedName("director")
    private final String director;

    public MovieSummary(int episode, String name, String description, String director, String url) {
        this.episode = episode;
        this.name = name;
        this.description = description;
        this.director = director;
        this.url = url;
    }

    public int getMovieID() {
        Uri uri = Uri.parse(url);
        String movieIdString = uri.getLastPathSegment();
        int movieId = Integer.valueOf(movieIdString);
        return movieId;
    }

    public int getEpisode() {
        return episode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MovieSummary that = (MovieSummary) o;
        return episode == that.episode &&
            Objects.equals(url, that.url) &&
            Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url, episode, name, description, director);
    }

    @Override
    public String toString() {
        return "MovieSummary{" +
            "url='" + url + '\'' +
            ", episode=" + episode +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", director='" + director + '\'' +
            '}';
    }
}
