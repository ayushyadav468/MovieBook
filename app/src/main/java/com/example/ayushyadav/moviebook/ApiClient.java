package com.example.ayushyadav.moviebook;

import com.example.ayushyadav.moviebook.Constants.Key;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ayushyadav on 25/03/18.
 */

public class ApiClient {

    private static ApiClient INSTANCE;

    private ApiCallInterface apiCallInterface;

    private ApiClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Key.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCallInterface = retrofit.create(ApiCallInterface.class);
    }

    public static ApiClient getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public ApiCallInterface getApiCallInterface() {
        return apiCallInterface;
    }

}
