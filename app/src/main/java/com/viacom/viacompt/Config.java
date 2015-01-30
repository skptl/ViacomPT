package com.viacom.viacompt;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Shilpan Patel on 1/29/15.
 */
public class Config {

    // URL for API
    public static final String apiUrl = "";

    // Debug flag
    public static final boolean DEBUG = false;

    // Check for network connectivity
    public static boolean isConnected(Context context){
        NetworkInfo info = Config.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    // Check if there is any connectivity to a Wifi network
    public static boolean isConnectedWifi(Context context){
        NetworkInfo info = Config.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    // Check if there is any connectivity to a mobile network
    public static boolean isConnectedMobile(Context context){
        NetworkInfo info = Config.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    // Get the network info
    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

}
