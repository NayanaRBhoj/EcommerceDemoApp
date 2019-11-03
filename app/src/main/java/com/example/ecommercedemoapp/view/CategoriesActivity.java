package com.example.ecommercedemoapp.view;

import android.widget.Toast;

import com.example.ecommercedemoapp.R;
import com.example.ecommercedemoapp.common.base.BaseActivity;
import com.example.ecommercedemoapp.common.base.BaseNavigator;
import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;
import com.example.ecommercedemoapp.databinding.ActivityCategoriesBinding;
import com.example.ecommercedemoapp.view.adapter.CategoriesAdapter;
import com.example.ecommercedemoapp.viewmodel.CategoriesViewModel;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

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
    public void success(List<CategoryList> list_categories,
                        List<CategoryList> list_suncategories,
                        List<CategoryList> list_products) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setupRecyclerView(list_categories);
            }
        });
    }

    @Override
    public void failure() {
        Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show();
    }

    public void setupRecyclerView(List<CategoryList> list) {
        mBinding.recyclerviewCategories.setLayoutManager(new LinearLayoutManager(this));
        CategoriesAdapter adapter = new CategoriesAdapter(this, list);
        mBinding.recyclerviewCategories.setAdapter(adapter);
    }
}
