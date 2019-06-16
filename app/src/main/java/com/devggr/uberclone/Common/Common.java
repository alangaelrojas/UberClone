package com.devggr.uberclone.Common;

import com.devggr.uberclone.Remote.IGoogleApi;
import com.devggr.uberclone.Remote.RetrofitClient;

public class Common {
    public static final String baseURL = "https:maps.googleapis.com";
    public static IGoogleApi getGoogleApi(){
        return RetrofitClient.getCliente(baseURL).create(IGoogleApi.class);
    }
}
