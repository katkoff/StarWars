package com.katkov.training_starwars.model.cloud;

import com.katkov.training_starwars.model.entities.Planet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class PlanetService {

    public void getPlanetDetails(final PlanetCallback callback, int planetId) {
        Methods.planetFetchCloudData(new Callback<Planet>() {
            @Override
            public void onResponse(
                Call<Planet> call, Response<Planet> response) {
                Planet planet = response.body();
                callback.onLoadSuccess(planet);
            }

            @Override
            public void onFailure(Call<Planet> call, Throwable t) {
                callback.onLoadFail();
            }
        }, planetId);
    }

    public interface PlanetCallback {
        void onLoadSuccess(Planet planet);

        void onLoadFail();
    }
}
