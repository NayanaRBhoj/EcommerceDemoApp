package com.example.ecommercedemoapp.common.repositories.database.entities;

import com.example.ecommercedemoapp.common.repositories.database.IntegerDataConverter;
import com.example.ecommercedemoapp.common.repositories.database.ProductDataConverter;
import com.example.ecommercedemoapp.model.StarkSpireItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "categories", indices = {@Index(value = {"id"}, unique = true)})
public class CategoryList {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @TypeConverters(ProductDataConverter.class)
    @ColumnInfo(name = "products")
    private List<StarkSpireItem.Product> products = null;
    @TypeConverters(IntegerDataConverter.class)
    @ColumnInfo(name = "child_categories")
    private List<Integer> childCategories = null;

    public CategoryList(int id, String name,List<StarkSpireItem.Product> products, List<Integer> childCategories) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.childCategories = childCategories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StarkSpireItem.Product> getProducts() {
        return products;
    }

    public void setProducts(List<StarkSpireItem.Product> products) {
        this.products = products;
    }

    public List<Integer> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Integer> childCategories) {
        this.childCategories = childCategories;
    }
}
