package com.katkov.training_starwars.ui.planet.list;

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
import com.katkov.training_starwars.model.entities.PlanetSummary;
import com.katkov.training_starwars.ui.OnRecyclerScrollEnd;
import com.katkov.training_starwars.ui.planet.details.PlanetActivity;

import java.util.List;

public final class PlanetListFragment extends MvpAppCompatFragment implements PlanetListView {

    @InjectPresenter
    PlanetListPresenter presenter;

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private PlanetListAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_planet_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    private void initViews(View rootView) {
        recyclerView = rootView.findViewById(R.id.planetsRecycler);
        adapter = new PlanetListAdapter(new PlanetListAdapter.ClickPlanetListCallback() {
            @Override
            public void onClickSuccess(int planetId) {
                presenter.onPlanetClick(planetId);
            }
        });
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new OnRecyclerScrollEnd(layoutManager) {
            @Override
            protected void onRecyclerScrollEnd() {
                presenter.onPaginate();
            }
        });
    }

    @Override
    public void displayPlanetList(List<PlanetSummary> list) {
        adapter.setPlanets(list);
    }

    @Override
    public void goToPlanet(int id) {
        Intent intent = new Intent(getActivity(), PlanetActivity.class);
        intent.putExtra(BundleConfig.PLANET_ID, id);
        startActivity(intent);
    }

    @Override
    public void displayLoadError() {
        Toast.makeText(getActivity(), getText(R.string.errorFetchPlanetSummary), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getText(R.string.waiting_text));
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public String toString() {
        return "Planets";
    }
}
