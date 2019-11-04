package com.example.ecommercedemoapp.viewmodel;

import android.os.AsyncTask;

import com.example.ecommercedemoapp.common.App;
import com.example.ecommercedemoapp.common.base.BaseViewModel;
import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;
import com.example.ecommercedemoapp.model.StarkSpireItem;
import com.example.ecommercedemoapp.view.ProductsNavigator;

import java.util.ArrayList;
import java.util.List;

public class ProductsViewModel extends BaseViewModel<ProductsNavigator> {
    public void getDataFromDB(int id) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<StarkSpireItem.Product> list_products = new ArrayList<>();
                    CategoryList categoryList = App.get().getDB().categoriesDao().getProductFromID(id);
                    list_products = categoryList.getProducts();
                    mNavigator.setData(list_products);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
