package com.example.ecommercedemoapp.viewmodel;

import android.content.Context;
import android.view.View;

import com.example.ecommercedemoapp.common.App;
import com.example.ecommercedemoapp.common.base.BaseViewModel;
import com.example.ecommercedemoapp.common.repository.networking.ApiServiceFactory;
import com.example.ecommercedemoapp.common.repository.networking.GenericCallback_Error;
import com.example.ecommercedemoapp.common.repository.networking.GenericCallback_Success;
import com.example.ecommercedemoapp.common.repository.networking.OriginalResponse;
import com.example.ecommercedemoapp.model.StarkSpireItem;
import com.example.ecommercedemoapp.view.CategoriesNavigator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.ecommercedemoapp.common.Utilities.isInternetAvailable;

public class CategoriesViewModel extends BaseViewModel<CategoriesNavigator> {
    Context mContext = App.get();

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
                        mNavigator.success(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                };

        OriginalResponse<Throwable> errorRes =
                (boolean status, Throwable t) -> {
                    progress.set(View.GONE);

                    return;

                };

        ApiServiceFactory.getApiService().getJsonData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GenericCallback_Success<>(successRes),
                        new GenericCallback_Error<>(errorRes));
    }
}
