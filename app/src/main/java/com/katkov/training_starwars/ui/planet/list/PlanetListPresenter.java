package com.katkov.training_starwars.ui.planet.list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katkov.training_starwars.model.cloud.PlanetListService;
import com.katkov.training_starwars.model.entities.PlanetSummary;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public final class PlanetListPresenter extends MvpPresenter<PlanetListView> {

    private final PlanetListService planetListService = new PlanetListService();
    private boolean isLoading = false;
    private int nextPageId;
    private int countPages;
    private List<PlanetSummary> list = new ArrayList<>();


    public void onPlanetClick(int id) {
        getViewState().goToPlanet(id);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        list.clear();
        nextPageId = 1;
        loadNextPage();
    }

    public void onPaginate() {
        loadNextPage();
    }

    private void loadNextPage() {
        if (list.size() > countPages
            || isLoading) {
            return;
        }

        isLoading = true;
        getViewState().showProgressDialog();

        if (nextPageId == 0) {
            getViewState().dismissProgressDialog();
            return;
        } else {
            planetListService.getPlanetList(new PlanetListService.PlanetListCallback() {
                @Override
                public void onLoadSuccess(List<PlanetSummary> pageList, int nextPageId, int countPages) {
                    isLoading = false;
                    PlanetListPresenter.this.nextPageId = nextPageId;
                    PlanetListPresenter.this.countPages = countPages;

                    list.addAll(pageList);
                    getViewState().displayPlanetList(list);
                    getViewState().dismissProgressDialog();
                }

                @Override
                public void onLoadFail() {
                    isLoading = false;
                    getViewState().dismissProgressDialog();
                    getViewState().displayLoadError();
                }
            }, nextPageId);
        }
    }
}
