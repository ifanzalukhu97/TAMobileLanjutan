package com.androidtutorialshub.tamobilelanjutan.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // TODO 3 APi Client

    public static final String BASE_URL = "http://10.0.2.2:5432";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){

        if (retrofit == null) {

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
