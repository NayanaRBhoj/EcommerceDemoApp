package com.example.ecommercedemoapp.view;

import com.example.ecommercedemoapp.common.base.BaseNavigator;
import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;
import com.example.ecommercedemoapp.model.StarkSpireItem;

import java.util.ArrayList;
import java.util.List;

public interface CategoriesNavigator extends BaseNavigator {
    void success(List<CategoryList> list_categories);
    void failure();
    void categoryClick();
}
