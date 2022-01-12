package com.demo.youbarajpoudeldemo.moreapps.CallIt;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.demo.youbarajpoudeldemo.moreapps.appmodels.AppsDetails;
import com.demo.youbarajpoudeldemo.moreapps.appmodels.IHAPI;
import com.demo.youbarajpoudeldemo.moreapps.appmodels.IHAdsData;
import com.demo.youbarajpoudeldemo.moreapps.appmodels.IhAdsDetail;
import com.demo.youbarajpoudeldemo.moreapps.appmodels.OnAdsResponse;
import com.demo.youbarajpoudeldemo.moreapps.appprefrences.AdsPrefernce;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class GetInhouseAds {
    Context context;
    ArrayList<AppsDetails> moreAppsArrayList;
    ArrayList<IhAdsDetail> ihAdsDetails;
    AdsPrefernce adsPrefernce;


    // constuctor for class
    public GetInhouseAds(Context context) {
        this.context = context;
        adsPrefernce = new AdsPrefernce(context);
    }

    // to check if internet is connected or not
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // calling InhouseAds api using retrofit
    public void getAds(OnAdsResponse onAdsResponse) {
        moreAppsArrayList = new ArrayList<>();
        ihAdsDetails = new ArrayList<>();
        IHAPI.apiInterface().getIHAds().enqueue(new retrofit2.Callback<IHAdsData>() {
            @Override
            public void onResponse(@NonNull Call<IHAdsData> call, @NonNull Response<IHAdsData> response) {
                IHAdsData ihAdsData = response.body();
                try {
                    if (ihAdsData.getIhAdsDetail() != null) {
                        ihAdsDetails = ihAdsData.getIhAdsDetail();
                        if (ihAdsDetails != null && ihAdsDetails.size() > 0) {
                            moreAppsArrayList.clear();
                            for (int i = 0; i < ihAdsDetails.size(); i++) {
                                if (ihAdsDetails.get(i).getShowad()) {
                                    moreAppsArrayList.add(new AppsDetails(ihAdsDetails.get(i).getIhads_id(),
                                            ihAdsDetails.get(i).getIcon(),
                                            ihAdsDetails.get(i).getTitle(),
                                            ihAdsDetails.get(i).getApplink(),
                                            ihAdsDetails.get(i).getShowad(),
                                            "",
                                            ihAdsDetails.get(i).getOpenin(),
                                            ihAdsDetails.get(i).getButtontext()));


                                }
                            }
                            adsPrefernce.setMoreAppsDetails(moreAppsArrayList);
                            adsPrefernce.setInHouseLoaded(true);
                            onAdsResponse.onResponseSuccess();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(@NonNull Call<IHAdsData> call, @NonNull Throwable t) {
                onAdsResponse.onResponseError();
            }
        });
    }

}
