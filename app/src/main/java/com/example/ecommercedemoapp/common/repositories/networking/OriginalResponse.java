package com.example.ecommercedemoapp.common.repositories.networking;

/**
 * Created by nayana on 24/8/18.
 */

public interface OriginalResponse<T> {
    void rawResponse(boolean status, T t);
}
