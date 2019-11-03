package com.example.ecommercedemoapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommercedemoapp.R;
import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    List<CategoryList> list;
    Context mContext;
    LayoutInflater inflater;

    public CategoriesAdapter(Context context, List<CategoryList> categoryList) {
        mContext = context;
        list = categoryList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.textName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
