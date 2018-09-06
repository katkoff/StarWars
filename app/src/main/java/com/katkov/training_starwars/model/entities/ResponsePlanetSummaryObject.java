package com.katkov.training_starwars.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ResponsePlanetSummaryObject {
    @SerializedName("count")
    private final int count;
    @SerializedName("next")
    private final String nextPageUrl;
    @SerializedName("results")
    private final List<PlanetSummary> planetSummaryList;

    public ResponsePlanetSummaryObject(List<PlanetSummary> planetSummaryList, int count, String nextPageUrl) {
        this.planetSummaryList = planetSummaryList;
        this.count = count;
        this.nextPageUrl = nextPageUrl;
    }

    public int getCount() {
        return count;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public List<PlanetSummary> getPlanetList() {
        return planetSummaryList;
    }
}
