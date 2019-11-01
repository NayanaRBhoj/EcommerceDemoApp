package com.example.ecommercedemoapp.common.repository.networking;

import io.reactivex.functions.Consumer;

public class GenericCallback_Error<T> implements Consumer<T> {

    OriginalResponse mListener;

    public GenericCallback_Error(OriginalResponse listener) {
        this.mListener = listener;
    }

    @Override
    public void accept(T t) throws Exception {
        if (t instanceof Throwable) {
            mListener.rawResponse(false, null);

        }
    }
}
