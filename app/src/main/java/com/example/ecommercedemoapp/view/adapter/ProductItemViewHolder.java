package com.example.ecommercedemoapp.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.ecommercedemoapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductItemViewHolder extends RecyclerView.ViewHolder {

    TextView titleTV, dateTV, taxNameTV, taxvalueTV, variantCountTV;

    public ProductItemViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTV = itemView.findViewById(R.id.titleTV);
        dateTV = itemView.findViewById(R.id.dateTV);
        taxNameTV = itemView.findViewById(R.id.taxNameTV);
        taxvalueTV = itemView.findViewById(R.id.taxvalueTV);
        variantCountTV = itemView.findViewById(R.id.variantCountTV);
    }
}
