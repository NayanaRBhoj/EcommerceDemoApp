package com.example.ecommercedemoapp.viewmodel;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.example.ecommercedemoapp.common.App;
import com.example.ecommercedemoapp.common.base.BaseViewModel;
import com.example.ecommercedemoapp.common.repositories.database.entities.CategoryList;
import com.example.ecommercedemoapp.common.repositories.networking.ApiServiceFactory;
import com.example.ecommercedemoapp.common.repositories.networking.GenericCallback_Error;
import com.example.ecommercedemoapp.common.repositories.networking.GenericCallback_Success;
import com.example.ecommercedemoapp.common.repositories.networking.OriginalResponse;
import com.example.ecommercedemoapp.model.StarkSpireItem;
import com.example.ecommercedemoapp.view.CategoriesNavigator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.ecommercedemoapp.common.Utilities.isInternetAvailable;

public class CategoriesViewModel extends BaseViewModel<CategoriesNavigator> {
    private Context mContext = App.get();

    public void getCategoriesWebCall() {
        if (!isInternetAvailable(mContext)) {
            return;
        }
        progress.set(View.VISIBLE);
        OriginalResponse<StarkSpireItem> successRes =
                (boolean statusCode, StarkSpireItem response) -> {
                    progress.set(View.GONE);
                    if (!statusCode) {
                        return;
                    }

                    try {

                        List<StarkSpireItem.Category> list_temp = response.getCategories();
                        List<CategoryList> list = new ArrayList<>();
                        for (int i = 0; i < list_temp.size(); i++) {
                            list.add(new CategoryList(
                                    list_temp.get(i).getId(),
                                    list_temp.get(i).getName(),
                                    list_temp.get(i).getProducts(),
                                    list_temp.get(i).getChildCategories()
                            ));
                        }
                        new insertIntoDB().execute(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                };

        OriginalResponse<Throwable> errorRes =
                (boolean status, Throwable t) -> {
                    progress.set(View.GONE);
                    mNavigator.failure();
                    return;

                };

        ApiServiceFactory.getApiService().getJsonData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GenericCallback_Success<>(successRes),
                        new GenericCallback_Error<>(errorRes));
    }

    public class insertIntoDB extends AsyncTask<List<CategoryList>, Void, Void> {
        protected Void doInBackground(List<CategoryList>... params) {
            App.get().getDB().categoriesDao().insertAll(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<CategoryList> list_categories = new ArrayList<>();
                        List<CategoryList> list_suncategories = new ArrayList<>();
                        List<CategoryList> list_products = new ArrayList<>();
                        List<CategoryList> list = App.get().getDB().categoriesDao().getAll();
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                if (list.get(i).getChildCategories() != null) {
                                    if (list.get(i).getChildCategories().size() > 0) {
                                        list_suncategories.add(list.get(i));
                                    } else {
                                        list_products.add(list.get(i));
                                    }

                                } else {

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i = 0; i < list_suncategories.size(); i++) {
                            try {
                                if (list_suncategories.get(i).getChildCategories() != null) {
                                    if (list_suncategories.get(i).getChildCategories().size() > 0) {
                                        List<Integer> temp = list_suncategories.get(i).getChildCategories();
                                        CategoryList temp_local = App.get().getDB().categoriesDao().getProductFromID(temp.get(0));
                                        if (temp_local.getChildCategories() != null) {
                                            if (temp_local.getChildCategories().size() > 0) {
                                                list_categories.add(list_suncategories.get(i));
                                            }
                                        }
                                    }
                                }
                                for (int k1 = 0; k1 < list_categories.size(); k1++) {
                                    for (int k2 = 0; k2 < list_suncategories.size(); k2++) {
                                        if (list_categories.get(k1).getId() == list_suncategories.get(k2).getId()) {
                                            list_suncategories.remove(k2);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        mNavigator.success(list_categories, list_suncategories, list_products);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }


}
