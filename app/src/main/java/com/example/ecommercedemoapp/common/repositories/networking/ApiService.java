package com.example.ecommercedemoapp.common.repositories.networking;

import com.example.ecommercedemoapp.model.StarkSpireItem;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL = "https://stark-spire-93433.herokuapp.com/";
    @GET("json")
    Observable<Response<StarkSpireItem>> getJsonData();
}
