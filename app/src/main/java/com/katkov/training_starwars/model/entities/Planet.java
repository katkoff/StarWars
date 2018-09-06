package com.katkov.training_starwars.model.entities;

import com.google.gson.annotations.SerializedName;

public final class Planet {
    @SerializedName("name")
    private final String name;
    @SerializedName("diameter")
    private final String diameter;
    @SerializedName("rotation_period")
    private final String rotationPeriod;
    @SerializedName("orbital_period")
    private final String orbitalPeriod;
    @SerializedName("population")
    private final String population;

    public Planet(String name, String diameter, String rotationPeriod, String orbitalPeriod, String population) {
        this.name = name;
        this.diameter = diameter;
        this.rotationPeriod = rotationPeriod;
        this.orbitalPeriod = orbitalPeriod;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getPopulation() {
        return population;
    }
}
