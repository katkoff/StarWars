package com.katkov.training_starwars.model.entities;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public final class CharacterSummary {

    @SerializedName("url")
    private final String url;
    @SerializedName("name")
    private final String name;
    @SerializedName("birth_year")
    private final String birthyear;


    public CharacterSummary(String url, String name, String birthyear) {
        this.url = url;
        this.name = name;
        this.birthyear = birthyear;
    }

    public int getCharacterID() {
        return getIdFormUrl(url);
    }

    private int getIdFormUrl(String url) {
        Uri uri = Uri.parse(url);
        String idString = uri.getLastPathSegment();
        int id = Integer.valueOf(idString);
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthyear() {
        return birthyear;
    }
}
