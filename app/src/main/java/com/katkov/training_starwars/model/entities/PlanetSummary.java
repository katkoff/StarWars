package com.katkov.training_starwars.model.entities;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public final class PlanetSummary {
    @SerializedName("name")
    private final String name;
    @SerializedName("url")
    private final String url;

    public PlanetSummary(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public int getPlanetID() {
        Uri uri = Uri.parse(url);
        String planetIdString = uri.getLastPathSegment();
        int planetId = Integer.valueOf(planetIdString);
        return planetId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
