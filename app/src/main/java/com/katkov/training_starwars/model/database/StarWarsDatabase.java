package com.katkov.training_starwars.model.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = StarWarsDatabase.NAME, version = StarWarsDatabase.VERSION)
public final class StarWarsDatabase {

    static final String NAME = "StarWarsDatabase";

    static final int VERSION = 1;
}
