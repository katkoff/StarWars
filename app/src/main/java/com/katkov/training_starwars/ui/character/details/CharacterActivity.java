package com.katkov.training_starwars.ui.character.details;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.katkov.training_starwars.App.BundleConfig;
import com.katkov.training_starwars.R;
import com.katkov.training_starwars.model.entities.Character;
import com.katkov.training_starwars.model.entities.Planet;
import com.katkov.training_starwars.model.entities.Species;
import com.katkov.training_starwars.ui.planet.details.PlanetActivity;

public final class CharacterActivity extends MvpAppCompatActivity implements CharacterView {

    @InjectPresenter
    CharacterPresenter presenter;

    private ProgressDialog progressDialog;
    private TextView nameTextView;
    private TextView heightTextView;
    private TextView massTextView;
    private TextView hairColorTextView;
    private TextView skinColorTextView;
    private TextView eyeColorColorTextView;
    private TextView genderTextView;
    private TextView birthyearTextView;
    private TextView homeworldTextView;
    private TextView speciesTextView;

    @ProvidePresenter
    CharacterPresenter provideCharacterPresenter() {
        return new CharacterPresenter(getIntent().getIntExtra(BundleConfig.CHARACTER_ID, 0));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        initViews();
    }

    private void initViews() {
        nameTextView = findViewById(R.id.textView_name);
        heightTextView = findViewById(R.id.textView_height);
        massTextView = findViewById(R.id.textView_mass);
        hairColorTextView = findViewById(R.id.textView_hairColor);
        skinColorTextView = findViewById(R.id.textView_skinColor);
        eyeColorColorTextView = findViewById(R.id.textView_eyeColor);
        genderTextView = findViewById(R.id.textView_gender);
        birthyearTextView = findViewById(R.id.textView_birthyear);
        homeworldTextView = findViewById(R.id.textView_homeWorld);
        speciesTextView = findViewById(R.id.textView_species);
    }

    @Override
    public void displayCharacter(Character character) {
        nameTextView.setText(character.getName());
        heightTextView.setText(character.getHeight());
        massTextView.setText(character.getMass());
        hairColorTextView.setText(character.getHairColor());
        skinColorTextView.setText(character.getSkinColor());
        eyeColorColorTextView.setText(character.getEyeColor());
        genderTextView.setText(character.getGender());
        birthyearTextView.setText(character.getBirthyear());
    }

    @Override
    public void displayUndefinedPlanetTemplate() {
        homeworldTextView.setText(getText(R.string.undefined));
    }

    @Override
    public void displayUndefinedSpeciesTemplate() {
        speciesTextView.setText(getText(R.string.undefined));
    }

    @Override
    public void displayPlanetName(Planet planet) {
        homeworldTextView.setText(planet.getName());
    }

    @Override
    public void displaySpeciesName(Species species) {
        speciesTextView.setText(species.getName());
    }

    @Override
    public void displayLoadError() {
        Toast.makeText(this, getText(R.string.errorFetchCharacter), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
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

    public void onClickHomePlanet(View view) {
        presenter.onPlanetClick();
    }

    @Override
    public void goToHomePlanet(int planetId) {
        Intent intent = new Intent(this, PlanetActivity.class);
        intent.putExtra(BundleConfig.PLANET_ID, planetId);
        startActivity(intent);
    }

    public void onPressedToFavoritesButton(View view) {
        presenter.onAddToFavouritesClick();
    }

    @Override
    public void displayAddToFavoritesMessage() {
        Toast.makeText(this, getText(R.string.addToFavorites), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayHavingTheSameRecordsMessage() {
        Toast.makeText(this, getText(R.string.havingTheSameRecords), Toast.LENGTH_SHORT).show();
    }
}
