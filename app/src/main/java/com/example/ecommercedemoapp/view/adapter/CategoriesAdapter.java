package com.example.ecommercedemoapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommercedemoapp.R;
import com.example.ecommercedemoapp.common.App;
import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;
import com.example.ecommercedemoapp.view.ProductsActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    List<CategoryList> list;
    Context mContext;
    LayoutInflater inflater;

    private final PublishSubject<Pair<Integer, CategoryList>> onClickSubject = PublishSubject.create();

    public Observable<Pair<Integer, CategoryList>> getPositionClicks() {
        return onClickSubject;
    }


    public CategoriesAdapter(Context context, List<CategoryList> categoryList) {
        mContext = context;
        list = categoryList;
        inflater = LayoutInflater.from(context);
    }

    //when needed to update
    public void updateList(List<CategoryList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        final CategoryList element = list.get(position);
        holder.textName.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair<Integer, CategoryList> pair;
                pair = new Pair<Integer, CategoryList>(position, element);
                onClickSubject.onNext(pair);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
