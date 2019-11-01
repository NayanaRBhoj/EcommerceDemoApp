package com.example.ecommercedemoapp.common.repository.networking;

import com.example.ecommercedemoapp.common.App;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {


    static OkHttpClient.Builder okHttpClientBuilder;
    private ApiServiceFactory() {
    }

    public static ApiService getApiService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder = new OkHttpClient.Builder();

        File httpCacheDirectory = new File(App.get().getApplicationContext().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient okHttpClient = okHttpClientBuilder
                .cache(cache)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // Logger for Api call
                .connectTimeout(60, TimeUnit.SECONDS) // 30 seconds Connection Timeout
                .readTimeout(60, TimeUnit.SECONDS) // 60 seconds Read Timeout
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // provides RxJava2 webservice call support here.
                .client(okHttpClient) // Sets OkHttpClient.
                .addConverterFactory(providesGsonConverterFactory()) // Set Gson converter here
                .baseUrl(ApiService.BASE_URL).build();
        return retrofit.create(ApiService.class);
    }


    private static GsonConverterFactory providesGsonConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return GsonConverterFactory.create(gsonBuilder.create());
    }
}
