package com.example.ecommercedemoapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommercedemoapp.R;
import com.example.ecommercedemoapp.model.StarkSpireItem;
import com.example.ecommercedemoapp.view.DetailsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemViewHolder> {

    List<StarkSpireItem.Product> list;
    Context mContext;
    LayoutInflater inflater;

    public ProductItemAdapter(Context context, List<StarkSpireItem.Product> list) {
        mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.items_productdetails, parent, false);
        return new ProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {

        holder.titleTV.setText(list.get(position).getName());
        holder.dateTV.setText(list.get(position).getDateAdded());
        StarkSpireItem.Tax tax = list.get(position).getTax();
        holder.taxNameTV.setText(tax.getName());
        holder.taxvalueTV.setText(Double.toString(tax.getValue()));
        int count = list.get(position).getVariants().size();
        if (count > 0) {
            holder.variantCountTV.setText(count + " Variants available");
        } else {
            holder.variantCountTV.setText("");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 0) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("product_id", list.get(position).getId());
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
