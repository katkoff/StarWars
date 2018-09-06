package com.katkov.training_starwars.model.cloud;

import com.katkov.training_starwars.model.entities.Character;
import com.katkov.training_starwars.model.entities.Movie;
import com.katkov.training_starwars.model.entities.Planet;
import com.katkov.training_starwars.model.entities.ResponseCharacterSummaryObject;
import com.katkov.training_starwars.model.entities.ResponseMovieSummaryObject;
import com.katkov.training_starwars.model.entities.ResponsePlanetSummaryObject;
import com.katkov.training_starwars.model.entities.Species;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET(Urls.API_FILMS)
    Call<ResponseMovieSummaryObject> getMovieSummaryList();

    @GET(Urls.API_FILMS + "{id}")
    Call<Movie> getMovie(@Path("id") int movieId);

    @GET(Urls.API_CHARACTERS)
    Call<ResponseCharacterSummaryObject> getCharacterSummaryList(@Query("page") int page);

    @GET(Urls.API_CHARACTERS + "{id}")
    Call<Character> getCharacter(@Path("id") int characterId);

    @GET(Urls.API_PLANETS)
    Call<ResponsePlanetSummaryObject> getPlanetSummaryList(@Query("page") int page);

    @GET(Urls.API_PLANETS + "{id}")
    Call<Planet> getPlanet(@Path("id") int planetId);

    @GET(Urls.API_SPECIES + "{id}")
    Call<Species> getSpecies(@Path("id") int speciesId);
}
