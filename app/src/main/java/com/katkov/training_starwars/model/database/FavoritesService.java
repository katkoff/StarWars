package com.katkov.training_starwars.model.database;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.List;

public final class FavoritesService {

    public void readFavoritesTableData(ReadFavoritesTableCallback callback) {
        List<FavoritesTable> table = SQLite.select()
            .from(FavoritesTable.class)
            .queryList();

        callback.onReadSuccess(table);
    }

    public void createFavoritesTableData(CreateFavoritesTableCallback callback, int idEntities, String title) {
        List<FavoritesTable> tables = SQLite.select()
            .from(FavoritesTable.class)
            .where(FavoritesTable_Table.idEntities.is(idEntities))
            .queryList();

        if (tables.size() == 0) {
            FavoritesTable favoritesTable = new FavoritesTable();
            favoritesTable.setIdEntities(idEntities);
            favoritesTable.setFavoriteName(title);

            ModelAdapter<FavoritesTable> adapter = FlowManager.getModelAdapter(FavoritesTable.class);
            adapter.insert(favoritesTable);

            callback.onCreateSuccess();
        } else {
            callback.onCreateFail();
            return;
        }
    }

    public void deleteFavoritesTableData(DeleteFavoritesTableCallback callback, int idEntities) {
        FavoritesTable table = SQLite.select()
            .from(FavoritesTable.class)
            .where(FavoritesTable_Table.idEntities.is(idEntities))
            .querySingle();

        if (table != null) {
            table.delete();
        } else {
            return;
        }

        callback.onDeleteSuccess(table);
    }

    public interface CreateFavoritesTableCallback {
        void onCreateSuccess();

        void onCreateFail();
    }

    public interface ReadFavoritesTableCallback {
        void onReadSuccess(List<FavoritesTable> table);
    }

    public interface DeleteFavoritesTableCallback {
        void onDeleteSuccess(FavoritesTable favoritesTable);
    }
}
