package com.katkov.training_starwars.model.entities;

import com.google.gson.annotations.SerializedName;

public final class Species {

    @SerializedName("name")
    private final String name;

    public Species(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
