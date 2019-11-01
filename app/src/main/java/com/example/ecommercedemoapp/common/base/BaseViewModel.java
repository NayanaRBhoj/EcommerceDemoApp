package com.example.ecommercedemoapp.common.base;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

public class BaseViewModel <N extends BaseNavigator> extends ViewModel {
    protected N mNavigator;
    public ObservableInt progress = new ObservableInt(View.GONE);

    public void setNavigator(N mNavigator) {
        this.mNavigator = mNavigator;
    }
}
