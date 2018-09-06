package com.katkov.training_starwars.ui.movie.details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.katkov.training_starwars.App.BundleConfig;
import com.katkov.training_starwars.R;
import com.katkov.training_starwars.model.entities.Movie;

public final class MovieActivity extends MvpAppCompatActivity implements MovieView {

    @InjectPresenter
    MoviePresenter presenter;
    private TextView titleTextView;
    private TextView directorTextView;
    private TextView openingCrawlTextView;
    private TextView releaseDateTextView;
    private ProgressDialog progressDialog;

    @ProvidePresenter
    MoviePresenter provideMoviePresenter() {
        return new MoviePresenter(
            getIntent().getIntExtra(BundleConfig.MOVIE_ID, 0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initViews();
    }

    private void initViews() {
        titleTextView = findViewById(R.id.textView_title);
        directorTextView = findViewById(R.id.textView_director);
        openingCrawlTextView = findViewById(R.id.textView_openingCrawl);
        releaseDateTextView = findViewById(R.id.textView_releaseDate);
    }

    @Override
    public void displayMovie(Movie movie) {
        titleTextView.setText(movie.getTitle());
        directorTextView.setText(movie.getDirector());
        openingCrawlTextView.setText(movie.getOpeningCrawl());
        releaseDateTextView.setText(movie.getReleaseDate());
    }

    @Override
    public void displayLoadError() {
        Toast.makeText(this, getText(R.string.errorFetchMovie), Toast.LENGTH_LONG).show();
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
