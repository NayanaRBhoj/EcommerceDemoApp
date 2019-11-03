package com.example.ecommercedemoapp.common.repositories.networking;

import io.reactivex.functions.Consumer;
import retrofit2.Response;

public class GenericCallback_Success<T> implements Consumer<Response<T>> {
    OriginalResponse mListener;

    public GenericCallback_Success(OriginalResponse listener) {
        this.mListener = listener;
    }

    @Override
    public void accept(Response<T> response) throws Exception {

        if (response.code() == 200 && response != null
                && response.isSuccessful() && response.body() != null) {
            mListener.rawResponse(true, response.body());

        } else {
            mListener.rawResponse(false, null);

        }

    }
}
