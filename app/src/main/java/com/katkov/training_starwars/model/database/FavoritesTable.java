package com.katkov.training_starwars.model.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = StarWarsDatabase.class)
public final class FavoritesTable extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    int idEntities;

    @Column
    int category;

    @Column
    private String favoriteName;

    public int getIdEntities() {
        return idEntities;
    }

    public void setIdEntities(int idEntities) {
        this.idEntities = idEntities;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }
}
