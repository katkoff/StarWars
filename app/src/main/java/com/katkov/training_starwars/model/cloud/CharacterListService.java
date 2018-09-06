package com.katkov.training_starwars.model.cloud;

import com.katkov.training_starwars.model.entities.CharacterSummary;
import com.katkov.training_starwars.model.entities.ResponseCharacterSummaryObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class CharacterListService {

    public void getCharacterList(final CharacterListCallback callback, int page) {

        Methods.characterListFetchCloudData(new Callback<ResponseCharacterSummaryObject>() {
            @Override
            public void onResponse(
                Call<ResponseCharacterSummaryObject> call, Response<ResponseCharacterSummaryObject> response) {

                int nextPageId;
                if (response.body().getNextPageUrl() == null) {
                    nextPageId = 0;
                } else {
                    String nextPageUrlString = response.body().getNextPageUrl();
                    nextPageId = UrlUtils.getNextPageId(nextPageUrlString);
                }

                int count = response.body().getCount();
                List<CharacterSummary> list = response.body().getCharactersList();
                callback.onLoadSuccess(list, nextPageId, count);
            }

            @Override
            public void onFailure(Call<ResponseCharacterSummaryObject> call, Throwable t) {
                t.printStackTrace();
                callback.onLoadFail();
            }
        }, page);
    }

    public interface CharacterListCallback {
        void onLoadSuccess(List<CharacterSummary> pageList, int nextPage, int countPages);

        void onLoadFail();
    }
}
