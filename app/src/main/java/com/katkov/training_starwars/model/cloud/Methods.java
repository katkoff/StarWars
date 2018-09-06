package com.katkov.training_starwars.model.cloud;

import com.katkov.training_starwars.model.entities.Character;
import com.katkov.training_starwars.model.entities.Movie;
import com.katkov.training_starwars.model.entities.Planet;
import com.katkov.training_starwars.model.entities.ResponseCharacterSummaryObject;
import com.katkov.training_starwars.model.entities.ResponseMovieSummaryObject;
import com.katkov.training_starwars.model.entities.ResponsePlanetSummaryObject;
import com.katkov.training_starwars.model.entities.Species;

import retrofit2.Call;
import retrofit2.Callback;

public final class Methods {

    public static void movieListFetchCloudData(Callback<ResponseMovieSummaryObject> cb) {
        Call<ResponseMovieSummaryObject> call = RetrofitConfig.getRetrofitService().getMovieSummaryList();
        call.enqueue(cb);
    }

    public static void movieFetchCloudData(Callback<Movie> cb, int movieId) {
        Call<Movie> call = RetrofitConfig.getRetrofitService().getMovie(movieId);
        call.enqueue(cb);
    }

    public static void characterListFetchCloudData(Callback<ResponseCharacterSummaryObject> cb, int page) {
        Call<ResponseCharacterSummaryObject> call = RetrofitConfig.getRetrofitService().getCharacterSummaryList(page);
        call.enqueue(cb);
    }

    public static void characterFetchCloudData(Callback<Character> cb, int characterId) {
        Call<Character> call = RetrofitConfig.getRetrofitService().getCharacter(characterId);
        call.enqueue(cb);
    }

    public static void planetListFetchCloudData(Callback<ResponsePlanetSummaryObject> cb, int page) {
        Call<ResponsePlanetSummaryObject> call = RetrofitConfig.getRetrofitService().getPlanetSummaryList(page);
        call.enqueue(cb);
    }

    public static void planetFetchCloudData(Callback<Planet> cb, int planetId) {
        Call<Planet> call = RetrofitConfig.getRetrofitService().getPlanet(planetId);
        call.enqueue(cb);
    }

    public static void speciesFetchCloudData(Callback<Species> cb, int speciesId) {
        Call<Species> call = RetrofitConfig.getRetrofitService().getSpecies(speciesId);
        call.enqueue(cb);
    }
}
