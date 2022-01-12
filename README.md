# YoubarajPoudel


// lib to show Ads in your app with easy steps











 // get request for inhouse ads from server
        GetInhouseAds getInhouseAds = new GetInhouseAds(this);
        getInhouseAds.getAds(new OnAdsResponse() {
            @Override
            public void onResponseSuccess() {

                // on getAds() response success
                // setting ads in Grid layout with "light" theme (*for dark theme use "dark")                        // layout set in activity_main.xml ( your activity xml )
                ShowMoreAppsLinear showMoreAppsGrid = new ShowMoreAppsLinear(YourActivity.this, "light", findViewById(R.id.lay_more_apps));
                showMoreAppsGrid.setInstallButtonTextColor(getResources().getColor(R.color.black));


                // To Show Inhouse Ads in horizontal scroll layout call
                // ShowMoreAppsLinear showMoreAppsLinear = new ShowMoreAppsLinear(MainActivity.this, "light", findViewById(R.id.lay_more_apps));


                // ** Modify your item layout ** //
                // set recycleview background
                //showMoreAppsGrid.setRvBackground(drawable);
                // set item background
                // showMoreAppsGrid.setItemBackground(drawable);
                // set "Ad" text background
                // showMoreAppsGrid.setAdbackground(drawable);
                // set "Install" button background
                // showMoreAppsGrid.setInstallButtonBackground(drawable);
                // set "Install" button text color
                // showMoreAppsGrid.setInstallButtonTextColor(color);
                // set AppName text colot
                // showMoreAppsGrid.setAppNameTextColor(color);


            }

            @Override
            public void onResponseError() {
                // on getAds() response error
            }
        });
