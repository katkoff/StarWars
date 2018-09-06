package com.katkov.training_starwars.ui.character.details;

import com.arellomobile.mvp.MvpView;
import com.katkov.training_starwars.model.entities.Character;
import com.katkov.training_starwars.model.entities.Planet;
import com.katkov.training_starwars.model.entities.Species;

public interface CharacterView extends MvpView {

    void displayCharacter(Character character);

    void displayUndefinedPlanetTemplate();

    void displayUndefinedSpeciesTemplate();

    void displayLoadError();

    void showProgressDialog();

    void dismissProgressDialog();

    void displayPlanetName(Planet planet);

    void displaySpeciesName(Species species);

    void goToHomePlanet(int planetId);

    void displayAddToFavoritesMessage();

    void displayHavingTheSameRecordsMessage();
}
