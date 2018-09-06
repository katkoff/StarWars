package com.katkov.training_starwars.ui.character.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katkov.training_starwars.model.cloud.CharacterService;
import com.katkov.training_starwars.model.cloud.PlanetService;
import com.katkov.training_starwars.model.cloud.SpeciesService;
import com.katkov.training_starwars.model.database.FavoritesService;
import com.katkov.training_starwars.model.entities.Character;
import com.katkov.training_starwars.model.entities.Planet;
import com.katkov.training_starwars.model.entities.Species;

@InjectViewState
public final class CharacterPresenter extends MvpPresenter<CharacterView> {

    private final PlanetService planetService = new PlanetService();
    private final CharacterService characterService = new CharacterService();
    private final SpeciesService speciesService = new SpeciesService();
    private final FavoritesService favoritesService = new FavoritesService();
    private int characterId;
    private int planetId;
    private String title;

    CharacterPresenter(int characterId) {
        this.characterId = characterId;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showProgressDialog();

        if (characterId == 0) {
            getViewState().displayLoadError();
        } else {
            characterService.getCharacterDetails(new CharacterService.CharacterCallback() {
                @Override
                public void onLoadSuccess(Character character) {
                    int planetId = character.getPlanetNameId();
                    int speciesId = character.getSpeciesNameId();
                    CharacterPresenter.this.title = character.getName();


                    getViewState().displayCharacter(character);

                    loadCloudPlanetData(planetId);

                    loadCloudSpeciesData(speciesId);
                }

                @Override
                public void onLoadFail() {
                    getViewState().dismissProgressDialog();
                    getViewState().displayLoadError();
                }
            }, characterId);
        }
    }

    private void loadCloudPlanetData(final int planetId) {
        if (planetId == 0) {
            getViewState().displayUndefinedPlanetTemplate();
        } else {
            planetService.getPlanetDetails(new PlanetService.PlanetCallback() {
                @Override
                public void onLoadSuccess(Planet planet) {
                    getViewState().displayPlanetName(planet);
                    getViewState().dismissProgressDialog();

                    CharacterPresenter.this.planetId = planetId;
                }

                @Override
                public void onLoadFail() {
                    getViewState().dismissProgressDialog();
                    getViewState().displayLoadError();
                }
            }, planetId);
        }
    }

    private void loadCloudSpeciesData(int speciesId) {
        if (speciesId == 0) {
            getViewState().displayUndefinedSpeciesTemplate();
        } else {
            speciesService.getSpeciesDetails(new SpeciesService.SpeciesCallback() {
                @Override
                public void onLoadSuccess(Species species) {
                    getViewState().displaySpeciesName(species);
                    getViewState().dismissProgressDialog();
                }

                @Override
                public void onLoadFail() {
                    getViewState().dismissProgressDialog();
                    getViewState().displayLoadError();
                }
            }, speciesId);
        }
    }

    public void onPlanetClick() {
        getViewState().goToHomePlanet(planetId);
    }

    public void onAddToFavouritesClick() {
        addToFavorites(characterId, title);
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
