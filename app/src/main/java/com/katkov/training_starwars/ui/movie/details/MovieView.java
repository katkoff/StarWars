package com.katkov.training_starwars.ui.movie.details;

import com.arellomobile.mvp.MvpView;
import com.katkov.training_starwars.model.entities.Movie;

public interface MovieView extends MvpView {
    void displayMovie(Movie movie);

    void displayLoadError();

    void showProgressDialog();

    void dismissProgressDialog();

    void displayAddToFavoritesMessage();

    void displayHavingTheSameRecordsMessage();
}
