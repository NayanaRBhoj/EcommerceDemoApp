package com.example.ecommercedemoapp.common.repositories.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class IntegerDataConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromOptionValuesList(List<Integer> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<Integer> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        List<Integer> productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

}