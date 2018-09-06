package com.katkov.training_starwars.ui.planet.list;

import com.arellomobile.mvp.MvpView;
import com.katkov.training_starwars.model.entities.PlanetSummary;

import java.util.List;

public interface PlanetListView extends MvpView {

    void displayPlanetList(List<PlanetSummary> list);

    void goToPlanet(int id);

    void displayLoadError();

    void showProgressDialog();

    void dismissProgressDialog();
}
