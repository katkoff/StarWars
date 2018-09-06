package com.katkov.training_starwars.App;

import android.app.Application;

import com.katkov.training_starwars.model.cloud.RetrofitConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public final class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RetrofitConfig.initRetrofit();
        RetrofitConfig.initMovieService();

        FlowManager.init(new FlowConfig.Builder(this).build());
    }
}
