package com.katkov.training_starwars.ui.movie.list;

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
import com.katkov.training_starwars.model.entities.MovieSummary;
import com.katkov.training_starwars.ui.movie.details.MovieActivity;

import java.util.List;


public final class MovieListFragment extends MvpAppCompatFragment implements MovieListView {

    @InjectPresenter
    MovieListPresenter presenter;

    private RecyclerView moviesRecycler;
    private MoviesAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(
        @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    private void initViews(View rootView) {
        moviesRecycler = rootView.findViewById(R.id.moviesRecycler);
        adapter = new MoviesAdapter(new MoviesAdapter.ClickMovieListCallback() {
            @Override
            public void onClickSuccess(int id) {
                presenter.onMovieClick(id);
            }
        });

        moviesRecycler.setAdapter(adapter);
        moviesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void displayMovieList(List<MovieSummary> list) {
        adapter.setMovies(list);
    }

    @Override
    public void goToMovie(int id) {
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra(BundleConfig.MOVIE_ID, id);

        this.startActivity(intent);
    }

    @Override
    public void displayLoadError() {
        Toast.makeText(getActivity(), getText(R.string.errorFetchMovieSummary), Toast.LENGTH_LONG).show();
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
        return "Movies";
    }

}
