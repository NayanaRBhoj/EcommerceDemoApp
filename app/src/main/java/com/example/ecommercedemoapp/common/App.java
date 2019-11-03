package com.example.ecommercedemoapp.common;

import android.app.Application;

import com.example.ecommercedemoapp.common.repositories.database.MyDatabase;

import androidx.room.Room;

public class App extends Application {

    public static App INSTANCE;

    public static App get() {
        return INSTANCE;
    }

    private static final String DATABASE_NAME = "MyDatabase";
    private MyDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        // create database
        database = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                .addMigrations(MyDatabase.MIGRATION_1_2)
                //.fallbackToDestructiveMigration()
                .build();

        INSTANCE = this;

    }

    public MyDatabase getDB() {
        return database;
    }
}
