package com.katkov.training_starwars.ui.movie.list;

import com.arellomobile.mvp.MvpView;
import com.katkov.training_starwars.model.entities.MovieSummary;

import java.util.List;

public interface MovieListView extends MvpView {

    void displayMovieList(List<MovieSummary> list);

    void goToMovie(int id);

    void displayLoadError();

    void showProgressDialog();

    void dismissProgressDialog();
}
