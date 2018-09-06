package com.katkov.training_starwars.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ResponseMovieSummaryObject {
    @SerializedName("results")
    private final List<MovieSummary> movieListSummary;

    public ResponseMovieSummaryObject(List<MovieSummary> movieListSummary) {
        this.movieListSummary = movieListSummary;
    }

    public List<MovieSummary> getMoviesList() {
        return movieListSummary;
    }
}
