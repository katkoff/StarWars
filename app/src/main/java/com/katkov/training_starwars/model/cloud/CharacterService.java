package com.katkov.training_starwars.model.cloud;

import com.katkov.training_starwars.model.entities.Character;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class CharacterService {

    public void getCharacterDetails(final CharacterCallback callback, int characterId) {
        Methods.characterFetchCloudData(new Callback<Character>() {
            @Override
            public void onResponse(
                Call<Character> call, Response<Character> response) {
                Character character = response.body();
                callback.onLoadSuccess(character);
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                t.printStackTrace();
                callback.onLoadFail();
            }
        }, characterId);
    }

    public interface CharacterCallback {
        void onLoadSuccess(Character character);

        void onLoadFail();
    }
}
