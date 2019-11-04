package com.example.ecommercedemoapp.view;

import com.example.ecommercedemoapp.R;
import com.example.ecommercedemoapp.common.base.BaseActivity;
import com.example.ecommercedemoapp.common.base.BaseNavigator;
import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;
import com.example.ecommercedemoapp.databinding.ActivityProductsitemsBinding;
import com.example.ecommercedemoapp.model.StarkSpireItem;
import com.example.ecommercedemoapp.view.adapter.CategoriesAdapter;
import com.example.ecommercedemoapp.view.adapter.ProductItemAdapter;
import com.example.ecommercedemoapp.viewmodel.ProductsViewModel;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

public class ProductsActivity extends BaseActivity<ActivityProductsitemsBinding, ProductsViewModel> implements ProductsNavigator {
    @Override
    public int getLayoutID() {
        return R.layout.activity_productsitems;
    }

    @Override
    public void onBinding() {
        mBinding.setProductsViewModel(mViewModel);

        int id = getIntent().getIntExtra("id", 0);
        mViewModel.getDataFromDB(id);
    }

    @Override
    public Class getViewModel() {
        return ProductsViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return this;
    }

    public void setupRecyclerView(List<StarkSpireItem.Product> list) {
        mBinding.recyclerviewCategories.setLayoutManager(new LinearLayoutManager(this));
        ProductItemAdapter adapter = new ProductItemAdapter(this, list);
        mBinding.recyclerviewCategories.setAdapter(adapter);
    }

    @Override
    public void setData(List<StarkSpireItem.Product> list) {
        setupRecyclerView(list);
    }
}
