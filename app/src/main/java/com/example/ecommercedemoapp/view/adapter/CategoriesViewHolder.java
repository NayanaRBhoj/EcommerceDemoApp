package com.example.ecommercedemoapp.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.ecommercedemoapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {
    TextView textName;

    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.text_name);
    }
}
