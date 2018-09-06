package com.katkov.training_starwars.model.cloud;

import com.katkov.training_starwars.model.entities.PlanetSummary;
import com.katkov.training_starwars.model.entities.ResponsePlanetSummaryObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class PlanetListService {

    public void getPlanetList(final PlanetListCallback callback, int page) {

        Methods.planetListFetchCloudData(new Callback<ResponsePlanetSummaryObject>() {
            @Override
            public void onResponse(
                Call<ResponsePlanetSummaryObject> call, Response<ResponsePlanetSummaryObject> response) {

                int nextPageId;
                if (response.body().getNextPageUrl() == null) {
                    nextPageId = 0;
                } else {
                    String nextPageUrlString = response.body().getNextPageUrl();
                    nextPageId = UrlUtils.getNextPageId(nextPageUrlString);
                }

                int countPages = response.body().getCount();
                List<PlanetSummary> list = response.body().getPlanetList();
                callback.onLoadSuccess(list, nextPageId, countPages);
            }

            @Override
            public void onFailure(Call<ResponsePlanetSummaryObject> call, Throwable t) {
                t.printStackTrace();
                callback.onLoadFail();
            }
        }, page);
    }

    public interface PlanetListCallback {
        void onLoadSuccess(List<PlanetSummary> list, int nextPage, int countPages);

        void onLoadFail();
    }
}
