package com.katkov.training_starwars.ui.character.list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.katkov.training_starwars.model.cloud.CharacterListService;
import com.katkov.training_starwars.model.entities.CharacterSummary;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public final class CharacterListPresenter extends MvpPresenter<CharacterListView> {

    private final CharacterListService characterListService = new CharacterListService();
    private boolean isLoading = false;
    private int nextPageId;
    private int countPages;
    private List<CharacterSummary> list = new ArrayList<>();

    public void onCharacterClick(int characterId) {
        getViewState().goToCharacter(characterId);
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
            characterListService.getCharacterList(new CharacterListService.CharacterListCallback() {
                @Override
                public void onLoadSuccess(List<CharacterSummary> pageList, int nextPageId, int countPages) {
                    isLoading = false;
                    CharacterListPresenter.this.nextPageId = nextPageId;
                    CharacterListPresenter.this.countPages = countPages;

                    list.addAll(pageList);
                    getViewState().displayCharacterList(list);
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
