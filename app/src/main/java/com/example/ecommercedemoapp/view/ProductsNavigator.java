package com.example.ecommercedemoapp.view;

import com.example.ecommercedemoapp.common.base.BaseNavigator;
import com.example.ecommercedemoapp.model.StarkSpireItem;

import java.util.List;

public interface ProductsNavigator extends BaseNavigator {
    void setData(List<StarkSpireItem.Product> list);
}
