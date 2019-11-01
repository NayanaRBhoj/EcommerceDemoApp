package com.example.ecommercedemoapp.common.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseActivity <VB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements UICallbacks {
    protected VB mBinding;
    protected VM mViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutID());
        mViewModel = (VM) ViewModelProviders.of(this).get(getViewModel());
        mViewModel.setNavigator(getNavigatorReference());
        onBinding();
    }
}
