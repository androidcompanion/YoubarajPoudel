package com.demo.youbarajpoudeldemo.moreapps.appprefrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.demo.youbarajpoudeldemo.moreapps.appmodels.AppsDetails;
import com.demo.youbarajpoudeldemo.moreapps.appmodels.IhAdsDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

// Common sharedPreference class for use in all app
public class AdsPrefernce {

    Context context;
    SharedPreferences adsPreference;
    SharedPreferences.Editor editor;

    public AdsPrefernce(Context context) {
        this.context = context;
        adsPreference = context.getSharedPreferences("MyAdsPrefrence", Context.MODE_PRIVATE);
        editor = adsPreference.edit();

    }

    public void setInHouseLoaded(boolean isLoaded) {
        editor.putBoolean("isInHouseAdLoaded", isLoaded);
        editor.apply();
    }

    public void setInHouseAdDetails(ArrayList<IhAdsDetail> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.remove("inHouseAdList");
        editor.apply();
        editor.putString("inHouseAdList", json);
        setInHouseLoaded(true);
        editor.apply();
    }

    public ArrayList<IhAdsDetail> getInHouseAds() {
        Gson gson = new Gson();
        String json = adsPreference.getString("inHouseAdList", "");
        Type type = new TypeToken<ArrayList<IhAdsDetail>>() {
        }.getType();
        ArrayList<IhAdsDetail> ihAdsDetails = gson.fromJson(json, type);
        return ihAdsDetails;
    }

    public void setMoreAppsDetails(ArrayList<AppsDetails> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.remove("moreAppList");
        editor.apply();
        editor.putString("moreAppList", json);
        editor.apply();
    }

    public void clearMoreAppsList() {
        editor.remove("moreAppList");
        editor.apply();
    }

    public ArrayList<AppsDetails> getMoreApps() {
        Gson gson = new Gson();
        String json = adsPreference.getString("moreAppList", "");
        Type type = new TypeToken<ArrayList<AppsDetails>>() {
        }.getType();
        ArrayList<AppsDetails> ihAdsDetails = gson.fromJson(json, type);
        return ihAdsDetails;
    }

    public Boolean isInHouseAdLoaded() {
        return adsPreference.getBoolean("isInHouseAdLoaded", false);
    }

}



