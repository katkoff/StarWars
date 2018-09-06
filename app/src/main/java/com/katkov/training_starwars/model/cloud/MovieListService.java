package com.katkov.training_starwars.model.cloud;

import com.katkov.training_starwars.model.entities.MovieSummary;
import com.katkov.training_starwars.model.entities.ResponseMovieSummaryObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class MovieListService {

    public void getMovieList(final MovieListCallback callback) {
        final List<MovieSummary> list = new ArrayList<>();

        Methods.movieListFetchCloudData(new Callback<ResponseMovieSummaryObject>() {
            @Override
            public void onResponse(
                Call<ResponseMovieSummaryObject> call, Response<ResponseMovieSummaryObject> response) {
                list.addAll(response.body().getMoviesList());
                callback.onLoadSuccess(list);
            }

            @Override
            public void onFailure(Call<ResponseMovieSummaryObject> call, Throwable t) {
                t.printStackTrace();
                callback.onLoadFail();
            }
        });

    }

    public interface MovieListCallback {
        void onLoadSuccess(List<MovieSummary> list);

        void onLoadFail();
    }
}
