package com.katkov.training_starwars.model.cloud;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitConfig {

    private static Retrofit retrofit;
    private static RetrofitService retrofitService;

    public static void initMovieService() {
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static void initRetrofit() {
        retrofit = new Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static RetrofitService getRetrofitService() {
        return retrofitService;
    }

    private RetrofitConfig() {
    }
}
