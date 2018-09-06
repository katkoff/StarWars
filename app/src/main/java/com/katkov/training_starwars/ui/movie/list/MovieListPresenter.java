package com.katkov.training_starwars.ui.movie.list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katkov.training_starwars.model.cloud.MovieListService;
import com.katkov.training_starwars.model.entities.MovieSummary;

import java.util.List;

@InjectViewState
public final class MovieListPresenter extends MvpPresenter<MovieListView> {

    private final MovieListService movieListService = new MovieListService();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showProgressDialog();
        movieListService.getMovieList(new MovieListService.MovieListCallback() {
            @Override
            public void onLoadSuccess(List<MovieSummary> list) {
                getViewState().displayMovieList(list);
            }

            @Override
            public void onLoadFail() {
                getViewState().dismissProgressDialog();
            }
        });
        getViewState().dismissProgressDialog();
    }

    public void onMovieClick(int id) {
        getViewState().goToMovie(id);
    }
}
