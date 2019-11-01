package com.example.ecommercedemoapp.common.repository.networking;

import com.example.ecommercedemoapp.model.StarkSpireItem;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL = "https://stark-spire-93433.herokuapp.com/json/";
    @GET()
    Observable<Response<StarkSpireItem>> getJsonData();
}
