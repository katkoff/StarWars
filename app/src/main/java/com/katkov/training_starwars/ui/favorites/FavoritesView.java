package com.katkov.training_starwars.ui.favorites;

import com.arellomobile.mvp.MvpView;
import com.katkov.training_starwars.model.database.FavoritesTable;

import java.util.List;

public interface FavoritesView extends MvpView {

    void displayFavoritesTable(List<FavoritesTable> favoritesTable);

    void displayLoadError();
}
