package com.katkov.training_starwars.model.entities;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class Character {

    @SerializedName("name")
    private final String name;
    @SerializedName("height")
    private final String height;
    @SerializedName("mass")
    private final String mass;
    @SerializedName("hair_color")
    private final String hairColor;
    @SerializedName("skin_color")
    private final String skinColor;
    @SerializedName("eye_color")
    private final String eyeColor;
    @SerializedName("gender")
    private final String gender;
    @SerializedName("birth_year")
    private final String birthyear;
    @SerializedName("homeworld")
    private final String homeworld;
    @SerializedName("species")
    private final List<String> species;

    public Character(
        String name,
        String height,
        String mass,
        String hairColor,
        String skinColor,
        String eyeColor,
        String gender,
        String birthyear,
        String homeworld,
        List<String> species) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.gender = gender;
        this.birthyear = birthyear;
        this.homeworld = homeworld;
        this.species = species;
    }

    private int getIdFormUrl(String url) {
        Uri uri = Uri.parse(url);
        String idString = uri.getLastPathSegment();
        int id = Integer.valueOf(idString);
        return id;
    }

    public List<String> getSpecies() {
        return species;
    }

    public int getSpeciesNameId() {
        return getIdFormUrl(species.get(0));
    }

    public int getPlanetNameId() {
        return getIdFormUrl(homeworld);
    }


    public String getHomeworld() {
        return homeworld;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthyear() {
        return birthyear;
    }
}
