package com.katkov.training_starwars.ui.planet.details;

import com.arellomobile.mvp.MvpView;
import com.katkov.training_starwars.model.entities.Planet;

public interface PlanetView extends MvpView {

    void displayPlanet(Planet planet);

    void displayLoadError();

    void showProgressDialog();

    void dismissProgressDialog();

    void displayAddToFavoritesMessage();

    void displayHavingTheSameRecordsMessage();
}
