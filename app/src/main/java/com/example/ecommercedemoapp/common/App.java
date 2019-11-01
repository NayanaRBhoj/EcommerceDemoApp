package com.example.ecommercedemoapp.common;

import android.app.Application;

public class App extends Application {

    public static App INSTANCE;

    public static App get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
