package com.example.ecommercedemoapp.common.base;

public interface UICallbacks {
    int getLayoutID();

    void onBinding();

    Class getViewModel();

    BaseNavigator getNavigatorReference();

}
