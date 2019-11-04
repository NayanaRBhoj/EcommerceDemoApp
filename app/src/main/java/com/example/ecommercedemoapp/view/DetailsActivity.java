package com.example.ecommercedemoapp.view;

import com.example.ecommercedemoapp.R;
import com.example.ecommercedemoapp.common.base.BaseActivity;
import com.example.ecommercedemoapp.common.base.BaseNavigator;
import com.example.ecommercedemoapp.databinding.ActivityDetailsBinding;
import com.example.ecommercedemoapp.viewmodel.DetailsViewModel;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsViewModel> implements DetailsNavigator {
    @Override
    public int getLayoutID() {
        return R.layout.activity_details;
    }

    @Override
    public void onBinding() {

    }

    @Override
    public Class getViewModel() {
        return DetailsViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return this;
    }
}
