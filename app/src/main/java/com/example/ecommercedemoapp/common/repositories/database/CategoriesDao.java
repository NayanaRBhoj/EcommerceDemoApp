package com.example.ecommercedemoapp.common.repositories.database;

import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CategoriesDao {

    @Query("SELECT * FROM categories")
    List<CategoryList> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CategoryList> activeDayzLists);

    @Query("SELECT * FROM categories where id = :id ")
    CategoryList getProductFromID(int id);

}
