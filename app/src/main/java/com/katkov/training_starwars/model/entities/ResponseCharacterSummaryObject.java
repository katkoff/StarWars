package com.katkov.training_starwars.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ResponseCharacterSummaryObject {
    @SerializedName("count")
    private final int count;
    @SerializedName("next")
    private final String nextPageUrl;
    @SerializedName("results")
    private final List<CharacterSummary> characterListSummary;

    public ResponseCharacterSummaryObject(
        List<CharacterSummary> characterListSummary,
        String nextPageUrl, int count) {
        this.characterListSummary = characterListSummary;
        this.nextPageUrl = nextPageUrl;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public List<CharacterSummary> getCharactersList() {
        return characterListSummary;
    }
}
