package com.example.ecommercedemoapp.common.repositories.database;

import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CategoryList.class},
        version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract CategoriesDao categoriesDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //for future requirement
        }
    };
}
