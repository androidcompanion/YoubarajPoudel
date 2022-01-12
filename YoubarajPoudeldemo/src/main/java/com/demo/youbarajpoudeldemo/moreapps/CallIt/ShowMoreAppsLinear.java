package com.demo.youbarajpoudeldemo.moreapps.CallIt;

import static com.demo.youbarajpoudeldemo.moreapps.CallIt.GetInhouseAds.isConnected;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.youbarajpoudeldemo.R;
import com.demo.youbarajpoudeldemo.moreapps.adapter.MoreappsAdapter;
import com.demo.youbarajpoudeldemo.moreapps.appmodels.AppsDetails;
import com.demo.youbarajpoudeldemo.moreapps.appprefrences.AdsPrefernce;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

// class to call apps in horizontal scroll
public class ShowMoreAppsLinear {

    Context context;
    String theme;
    RecyclerView rvapps;
    ShimmerFrameLayout shimmerGrid, shimmerLinear;
    TextView tv_seemore;
    LinearLayout ll_moreApps;
    LinearLayout moreView;
    ViewGroup view;
    public static int installTextColor = 0;
    public static int appNameTextColor = 0;
    public static Drawable adBackground = null;
    public static Drawable installButtonBackground = null;
    public static Drawable rvBackground = null;
    public static Drawable itemBackground = null;
    List<AppsDetails> appsDetailsArrayList;

    //constructor
    public ShowMoreAppsLinear(Context context, String theme, ViewGroup view) {
        this.context = context;
        this.theme = theme;
        this.view = view;
        this.view.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(context);
        moreView = (LinearLayout) inflater.inflate(R.layout.layout_more_apps, view, false);
             showMore(view);

    }

    public void setRvBackground(Drawable drawable) {
        if (drawable != null) {
            rvapps.setBackground(rvBackground);
        }
    }

    public void setSeeMoreButtonBackground(Drawable drawable) {
        if (drawable != null) {
            tv_seemore.setBackground(drawable);
        }
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null) {
            itemBackground = drawable;
        }
    }

    public void setSeeMoreButtonTextColor(int color) {
        tv_seemore.setTextColor(color);
    }

    public void setAdbackground(Drawable drawable) {
        adBackground = drawable;
    }

    public void setInstallButtonBackground(Drawable drawable) {
        installButtonBackground = drawable;
    }

    public void setInstallButtonTextColor(int color) {
        installTextColor = color;
    }

    public void setAppNameTextColor(int color) {
        appNameTextColor = color;
    }

    public static Drawable getAdBackground() {
        return adBackground;
    }

    public static Drawable getInstallButtonBackground() {
        return installButtonBackground;
    }

    public static int getInstallTextColor() {
        return installTextColor;
    }

    public static int getAppNameTextColor() {
        return appNameTextColor;
    }

    public static Drawable getItemBackground() {
        return itemBackground;
    }

    public void showMore(ViewGroup view) {
        view.addView(moreView);
        tv_seemore = moreView.findViewById(R.id.tv_seemore);
        ll_moreApps = moreView.findViewById(R.id.ll_moreApps);

        rvapps = moreView.findViewById(R.id.rvapps);
        shimmerGrid = moreView.findViewById(R.id.shimmerGrid);
        shimmerLinear = moreView.findViewById(R.id.shimmerLinear);

        AdsPrefernce adsPrefernce = new AdsPrefernce(context);
        if (isConnected(context) && adsPrefernce.isInHouseAdLoaded()) {

            shimmerGrid.setVisibility(View.GONE);
            shimmerLinear.setVisibility(View.VISIBLE);
        }
        rvapps.setVisibility(View.GONE);

        if (adsPrefernce.isInHouseAdLoaded()){

            appsDetailsArrayList = new ArrayList<>();
            appsDetailsArrayList = adsPrefernce.getMoreApps();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    shimmerGrid.setVisibility(View.GONE);
                    shimmerLinear.setVisibility(View.GONE);
                    rvapps.setVisibility(View.VISIBLE);
                    MoreappsAdapter adapter = new MoreappsAdapter(context, appsDetailsArrayList, "linear", theme, getAppNameTextColor(), getInstallButtonBackground(), getInstallTextColor(), getAdBackground(), getItemBackground());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                    rvapps.setLayoutManager(layoutManager);
                    rvapps.setItemAnimator(new DefaultItemAnimator());
                    rvapps.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            },1000);



        }else {
            shimmerGrid.setVisibility(View.GONE);
            shimmerLinear.setVisibility(View.GONE);
            rvapps.setVisibility(View.GONE);
        }
    }
}
