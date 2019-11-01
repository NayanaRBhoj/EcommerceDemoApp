package com.example.ecommercedemoapp.view;

import com.example.ecommercedemoapp.R;
import com.example.ecommercedemoapp.common.base.BaseActivity;
import com.example.ecommercedemoapp.common.base.BaseNavigator;
import com.example.ecommercedemoapp.databinding.ActivityCategoriesBinding;
import com.example.ecommercedemoapp.model.StarkSpireItem;
import com.example.ecommercedemoapp.viewmodel.CategoriesViewModel;

public class CategoriesActivity extends BaseActivity<ActivityCategoriesBinding, CategoriesViewModel> implements CategoriesNavigator {


    @Override
    public int getLayoutID() {
        return R.layout.activity_categories;
    }

    @Override
    public void onBinding() {
        mBinding.setCategoriesViewModel(mViewModel);
        mViewModel.getCategoriesWebCall();
    }

    @Override
    public Class getViewModel() {
        return CategoriesViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return this;
    }

    @Override
    public void success(StarkSpireItem response) {
        mBinding.text.setText(response.toString());
    }
}
