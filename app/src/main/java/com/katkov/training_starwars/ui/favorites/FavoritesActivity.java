package com.katkov.training_starwars.ui.favorites;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katkov.training_starwars.R;
import com.katkov.training_starwars.model.database.FavoritesTable;

import java.util.ArrayList;
import java.util.List;

public final class FavoritesActivity extends MvpAppCompatActivity implements FavoritesView {

    @InjectPresenter
    FavoritesPresenter presenter;
    private FavoritesAdapter adapter;
    private RecyclerView favoritesRecycler;
    private List<FavoritesTable> tables = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        initViews();
    }

    private void initViews() {
        favoritesRecycler = findViewById(R.id.favouritesRecycler);
        favoritesRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavoritesAdapter();
        favoritesRecycler.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(
                RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int id = tables.get(viewHolder.getAdapterPosition()).getIdEntities();
                presenter.onDeleteRecords(id);
                tables.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(favoritesRecycler);
    }

    @Override
    public void displayFavoritesTable(List<FavoritesTable> favoritesTable) {
        adapter.setFavorites(favoritesTable);
        this.tables = favoritesTable;
    }

    @Override
    public void displayLoadError() {
        Toast.makeText(this, getText(R.string.errorFetchFavoritesTable), Toast.LENGTH_LONG).show();
    }
}
