package com.example.ecommercedemoapp.common.repositories.database;

import com.example.ecommercedemoapp.model.StarkSpireItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ProductDataConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromOptionValuesList(List<StarkSpireItem.Product> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StarkSpireItem.Product>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<StarkSpireItem.Product> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StarkSpireItem.Product>>() {
        }.getType();
        List<StarkSpireItem.Product> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

}