package com.katkov.training_starwars.model.cloud;

import com.katkov.training_starwars.model.entities.Species;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class SpeciesService {

    public void getSpeciesDetails(final SpeciesCallback callback, int speciesId) {
        Methods.speciesFetchCloudData(new Callback<Species>() {
            @Override
            public void onResponse(
                Call<Species> call, Response<Species> response) {
                Species species = response.body();
                callback.onLoadSuccess(species);
            }

            @Override
            public void onFailure(Call<Species> call, Throwable t) {
                t.printStackTrace();
                callback.onLoadFail();
            }
        }, speciesId);
    }

    public interface SpeciesCallback {
        void onLoadSuccess(Species species);

        void onLoadFail();
    }
}
