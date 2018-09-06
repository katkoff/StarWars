package com.katkov.training_starwars.ui.character.list;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katkov.training_starwars.App.BundleConfig;
import com.katkov.training_starwars.R;
import com.katkov.training_starwars.model.entities.CharacterSummary;
import com.katkov.training_starwars.ui.OnRecyclerScrollEnd;
import com.katkov.training_starwars.ui.character.details.CharacterActivity;

import java.util.List;

public final class CharacterListFragment extends MvpAppCompatFragment implements CharacterListView {

    @InjectPresenter
    CharacterListPresenter presenter;

    private RecyclerView charactersRecycler;
    private CharactersAdapter adapter;
    private ProgressDialog progressDialog;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    private void initViews(View rootView) {
        charactersRecycler = rootView.findViewById(R.id.charactersRecycler);
        adapter = new CharactersAdapter(new CharactersAdapter.ClickCharacterListCallback() {
            @Override
            public void onClickSuccess(int characterId) {
                presenter.onCharacterClick(characterId);
            }
        });
        charactersRecycler.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        charactersRecycler.setLayoutManager(layoutManager);
        charactersRecycler.addOnScrollListener(new OnRecyclerScrollEnd(layoutManager) {
            @Override
            protected void onRecyclerScrollEnd() {
                presenter.onPaginate();
            }
        });
    }

    @Override
    public void displayCharacterList(List<CharacterSummary> list) {
        adapter.setCharacters(list);
    }

    @Override
    public void goToCharacter(int characterId) {
        Intent intent = new Intent(getActivity(), CharacterActivity.class);
        intent.putExtra(BundleConfig.CHARACTER_ID, characterId);

        startActivity(intent);
    }

    @Override
    public void displayLoadError() {
        Toast.makeText(getActivity(), getText(R.string.errorFetchCharacterSummary), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getText(R.string.waiting_text));
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog == null) {
            return;
        }
        progressDialog.dismiss();
    }

    @Override
    public String toString() {
        return "Characters";
    }
}
