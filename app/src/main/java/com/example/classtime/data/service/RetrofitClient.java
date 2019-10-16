package com.example.classtime.data.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit;
    private String BASE_URL = "http://192.168.2.157:3000";
//    private String BASE_URL = "http://10.11.171.63:3000";

    public ClassTimeService getClassTimeService() {

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }

        return retrofit.create(ClassTimeService.class);
    }
}
