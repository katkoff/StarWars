package com.katkov.training_starwars.ui.character.list;

import com.arellomobile.mvp.MvpView;
import com.katkov.training_starwars.model.entities.CharacterSummary;

import java.util.List;

public interface CharacterListView extends MvpView {

    void displayCharacterList(List<CharacterSummary> list);

    void goToCharacter(int characterId);

    void displayLoadError();

    void showProgressDialog();

    void dismissProgressDialog();
}
