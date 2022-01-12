package com.demo.youbarajpoudeldemo.moreapps.appmodels;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IHAPI {

    // initialization of retrofit with BaseUrl(API)
    private static <T> T builder(Class<T> endpoint) {
        return new Retrofit.Builder()
                .baseUrl("https://script.google.com/macros/s/AKfycbx7xvF4v9hhCt72gDPR14-b5xsCoqB869oPrWELORx14IowHnv0mkMifzCcDDrJpzcpuA/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoint);
    }

    public static ApiInterface apiInterface() {
        return builder(ApiInterface.class);
    }

}
