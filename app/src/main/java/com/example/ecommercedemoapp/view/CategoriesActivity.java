package com.example.ecommercedemoapp.view;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.ecommercedemoapp.R;
import com.example.ecommercedemoapp.common.base.BaseActivity;
import com.example.ecommercedemoapp.common.base.BaseNavigator;
import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;
import com.example.ecommercedemoapp.databinding.ActivityCategoriesBinding;
import com.example.ecommercedemoapp.view.adapter.CategoriesAdapter;
import com.example.ecommercedemoapp.viewmodel.CategoriesViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.functions.Consumer;

public class CategoriesActivity extends BaseActivity<ActivityCategoriesBinding, CategoriesViewModel> implements CategoriesNavigator {

    String tabSelected = "categories";
    List<CategoryList> list_categories;
    CategoriesAdapter adapter;

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
    public void success(List<CategoryList> list_categories) {
        this.list_categories = list_categories;
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


    @Override
    public void categoryClick() {

        tabSelected = "categories";
        mBinding.llAll.setBackgroundColor(getResources().getColor(R.color.white));
        mBinding.textAll.setTextColor(getResources().getColor(R.color.black));
        mBinding.viewAll.setVisibility(View.VISIBLE);

        mBinding.llEarn.setBackgroundColor(getResources().getColor(R.color.grey3));
        mBinding.textEarn.setTextColor(getResources().getColor(R.color.grey1));
        mBinding.viewEarn.setVisibility(View.GONE);

        mBinding.llBurn.setBackgroundColor(getResources().getColor(R.color.grey3));
        mBinding.textBurn.setTextColor(getResources().getColor(R.color.grey1));
        mBinding.viewBurn.setVisibility(View.GONE);


        if (list_categories.size() > 0) {
            mBinding.recyclerviewCategories.setVisibility(View.VISIBLE);
            mBinding.textNoresponse.setVisibility(View.GONE);
            mViewModel.getCategoriesWebCall();
        }


    }

    public void setupRecyclerView(List<CategoryList> list) {
        mBinding.recyclerviewCategories.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoriesAdapter(this, list);
        mBinding.recyclerviewCategories.setAdapter(adapter);
        adapter.getPositionClicks().subscribe(mClickConsumer_Earn);
    }

    Consumer<Pair<Integer, CategoryList>> mClickConsumer_Earn = new Consumer<Pair<Integer, CategoryList>>() {
        @Override
        public void accept(@NonNull Pair<Integer, CategoryList> element) throws Exception {
            if(tabSelected.equalsIgnoreCase("categories")){
                tabSelected = "subcategories";
                mBinding.llAll.setBackgroundColor(getResources().getColor(R.color.grey3));
                mBinding.textAll.setTextColor(getResources().getColor(R.color.grey1));
                mBinding.viewAll.setVisibility(View.GONE);

                mBinding.llEarn.setBackgroundColor(getResources().getColor(R.color.white));
                mBinding.textEarn.setTextColor(getResources().getColor(R.color.black));
                mBinding.viewEarn.setVisibility(View.VISIBLE);

                mBinding.llBurn.setBackgroundColor(getResources().getColor(R.color.grey3));
                mBinding.textBurn.setTextColor(getResources().getColor(R.color.grey1));
                mBinding.viewBurn.setVisibility(View.GONE);
            } else if(tabSelected.equalsIgnoreCase("subcategories")){
                tabSelected = "products";
                mBinding.llAll.setBackgroundColor(getResources().getColor(R.color.grey3));
                mBinding.textAll.setTextColor(getResources().getColor(R.color.grey1));
                mBinding.viewAll.setVisibility(View.GONE);

                mBinding.llEarn.setBackgroundColor(getResources().getColor(R.color.grey3));
                mBinding.textEarn.setTextColor(getResources().getColor(R.color.grey1));
                mBinding.viewEarn.setVisibility(View.GONE);

                mBinding.llBurn.setBackgroundColor(getResources().getColor(R.color.white));
                mBinding.textBurn.setTextColor(getResources().getColor(R.color.black));
                mBinding.viewBurn.setVisibility(View.VISIBLE);
            } else {

            }
            if (element.second.getChildCategories() != null && element.second.getChildCategories().size() > 0) {
                mViewModel.getValuesById(element.second.getId());
            } else {
                Intent intent = new Intent(CategoriesActivity.this, ProductsActivity.class);
                intent.putExtra("id", element.second.getId());
                startActivity(intent);
            }
        }
    };
}
