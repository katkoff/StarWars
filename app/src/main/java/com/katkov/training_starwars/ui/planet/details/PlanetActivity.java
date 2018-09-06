package com.katkov.training_starwars.ui.planet.details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.katkov.training_starwars.App.BundleConfig;
import com.katkov.training_starwars.R;
import com.katkov.training_starwars.model.entities.Planet;

public final class PlanetActivity extends MvpAppCompatActivity implements PlanetView {

    @InjectPresenter
    PlanetPresenter presenter;
    private TextView nameTextView;
    private TextView diameterTextView;
    private TextView rotationPeriodTextView;
    private TextView orbitalPeriodTextView;
    private TextView populationTextView;
    private ProgressDialog progressDialog;

    @ProvidePresenter
    PlanetPresenter providePlanetPresenter() {
        return new PlanetPresenter(getIntent().getIntExtra(BundleConfig.PLANET_ID, 0));
    }

    @Override
    public void onCreate(
        @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        initViews();
    }

    private void initViews() {
        initTextFields();
    }

    private void initTextFields() {
        nameTextView = findViewById(R.id.textView_name);
        diameterTextView = findViewById(R.id.textView_diameter);
        rotationPeriodTextView = findViewById(R.id.textView_rotationPeriod);
        orbitalPeriodTextView = findViewById(R.id.textView_orbitalPeriod);
        populationTextView = findViewById(R.id.textView_population);
    }

    @Override
    public void displayPlanet(Planet planet) {
        nameTextView.setText(planet.getName());
        diameterTextView.setText(planet.getDiameter());
        rotationPeriodTextView.setText(planet.getRotationPeriod());
        orbitalPeriodTextView.setText(planet.getOrbitalPeriod());
        populationTextView.setText(planet.getPopulation());
    }

    @Override
    public void displayLoadError() {
        Toast.makeText(this, getText(R.string.errorFetchPlanet), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getText(R.string.waiting_text));
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void displayAddToFavoritesMessage() {
        Toast.makeText(this, getText(R.string.addToFavorites), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayHavingTheSameRecordsMessage() {
        Toast.makeText(this, getText(R.string.havingTheSameRecords), Toast.LENGTH_SHORT).show();
    }

    public void onPressedToFavoritesButton(View view) {
        presenter.onAddToFavouritesClick();
    }
}
