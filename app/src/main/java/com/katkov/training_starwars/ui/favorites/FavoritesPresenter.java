package com.katkov.training_starwars.ui.favorites;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katkov.training_starwars.model.database.FavoritesService;
import com.katkov.training_starwars.model.database.FavoritesTable;

import java.util.List;

@InjectViewState
public final class FavoritesPresenter extends MvpPresenter<FavoritesView> {

    private FavoritesService favoritesService = new FavoritesService();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        favoritesService.readFavoritesTableData(new FavoritesService.ReadFavoritesTableCallback() {
            @Override
            public void onReadSuccess(List<FavoritesTable> table) {
                getViewState().displayFavoritesTable(table);
            }
        });
    }

    public void onDeleteRecords(int idEntities) {
        favoritesService.deleteFavoritesTableData(new FavoritesService.DeleteFavoritesTableCallback() {
            @Override
            public void onDeleteSuccess(FavoritesTable table) {
            }
        }, idEntities);
    }
}
