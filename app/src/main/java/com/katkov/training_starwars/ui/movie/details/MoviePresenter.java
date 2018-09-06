package com.katkov.training_starwars.ui.movie.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katkov.training_starwars.model.cloud.MovieService;
import com.katkov.training_starwars.model.database.FavoritesService;
import com.katkov.training_starwars.model.entities.Movie;

@InjectViewState
public final class MoviePresenter extends MvpPresenter<MovieView> {

    private int movieId;
    private final FavoritesService favoritesService = new FavoritesService();
    private final MovieService movieService = new MovieService();
    private String title;

    MoviePresenter(int movieId) {
        this.movieId = movieId;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        if (movieId == 0) {
            getViewState().displayLoadError();
            return;
        }

        getViewState().showProgressDialog();
        movieService.getMovieDetails(new MovieService.MovieCallback() {
            @Override
            public void onLoadSuccess(Movie movie) {
                getViewState().displayMovie(movie);
                getViewState().dismissProgressDialog();

                MoviePresenter.this.title = movie.getTitle();
            }

            @Override
            public void onLoadFail() {
                getViewState().dismissProgressDialog();
                getViewState().displayLoadError();
            }
        }, movieId);
    }

    public void onAddToFavouritesClick() {
        addToFavorites(movieId, title);
        updateFavoritesButton();
    }

    private void addToFavorites(int idEntities, String title) {
        favoritesService.createFavoritesTableData(new FavoritesService.CreateFavoritesTableCallback() {
            @Override
            public void onCreateSuccess() {
                getViewState().displayAddToFavoritesMessage();
            }

            @Override
            public void onCreateFail() {
                getViewState().displayHavingTheSameRecordsMessage();
            }
        }, idEntities, title);
    }

    private void updateFavoritesButton() {
    }
}