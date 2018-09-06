package com.katkov.training_starwars.model.cloud;

import com.katkov.training_starwars.model.entities.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class MovieService {

    public void getMovieDetails(final MovieCallback callback, int movieId) {

        Methods.movieFetchCloudData(new Callback<Movie>() {
            @Override
            public void onResponse(
                Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                callback.onLoadSuccess(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
                callback.onLoadFail();
            }
        }, movieId);
    }

    public interface MovieCallback {
        void onLoadSuccess(Movie movie);

        void onLoadFail();
    }
}
