package com.demo.youbarajpoudeldemo.moreapps.appmodels;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    // getAds() interface
    @GET("exec")
    Call<IHAdsData> getIHAds();


}
