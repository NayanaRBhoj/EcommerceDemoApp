package com.example.ecommercedemoapp.view;

import com.example.ecommercedemoapp.common.base.BaseNavigator;
import com.example.ecommercedemoapp.model.StarkSpireItem;

public interface CategoriesNavigator extends BaseNavigator {
    void success(StarkSpireItem response);
}
