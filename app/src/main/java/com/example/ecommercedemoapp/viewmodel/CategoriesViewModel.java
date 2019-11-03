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
            mNavigator.success();
        }
    }


}
