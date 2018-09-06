package com.katkov.training_starwars.ui.planet.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katkov.training_starwars.model.cloud.PlanetService;
import com.katkov.training_starwars.model.database.FavoritesService;
import com.katkov.training_starwars.model.entities.Planet;

@InjectViewState
public final class PlanetPresenter extends MvpPresenter<PlanetView> {

    private int planetId;
    private String title;
    private final PlanetService planetService = new PlanetService();
    private final FavoritesService favoritesService = new FavoritesService();

    PlanetPresenter(int planetId) {
        this.planetId = planetId;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        if (planetId == 0) {
            getViewState().displayLoadError();
            return;
        }

        getViewState().showProgressDialog();
        planetService.getPlanetDetails(new PlanetService.PlanetCallback() {
            @Override
            public void onLoadSuccess(Planet planet) {
                getViewState().displayPlanet(planet);
                getViewState().dismissProgressDialog();

                PlanetPresenter.this.title = planet.getName();
            }

            @Override
            public void onLoadFail() {
                getViewState().dismissProgressDialog();
                getViewState().displayLoadError();
            }
        }, planetId);
    }

    public void onAddToFavouritesClick() {
        addToFavorites(planetId, title);
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
