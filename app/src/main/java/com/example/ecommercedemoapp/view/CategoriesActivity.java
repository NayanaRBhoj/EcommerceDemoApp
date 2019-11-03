package com.example.ecommercedemoapp.view;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ecommercedemoapp.R;
import com.example.ecommercedemoapp.common.App;
import com.example.ecommercedemoapp.common.base.BaseActivity;
import com.example.ecommercedemoapp.common.base.BaseNavigator;
import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;
import com.example.ecommercedemoapp.databinding.ActivityCategoriesBinding;
import com.example.ecommercedemoapp.view.adapter.CategoriesAdapter;
import com.example.ecommercedemoapp.viewmodel.CategoriesViewModel;

import java.util.ArrayList;
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
    public void success() {
        //mBinding.text.setText(response.toString());
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CategoryList> list_final= new ArrayList<>();
                    List<CategoryList> list  = App.get().getDB().categoriesDao().getAll();
                    for (int i = 0; i < list.size(); i++) {
                        try {
                            if (list.get(i).getChildCategories() != null) {
                                if (list.get(i).getChildCategories().size() > 0)
                                    list_final.add(list.get(i));
                            }
                            //setupRecyclerView(list);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setupRecyclerView(list_final);
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
