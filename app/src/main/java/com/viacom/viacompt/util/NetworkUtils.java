package com.viacom.viacompt.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;

import com.turbomanage.httpclient.BasicHttpClient;
import com.turbomanage.httpclient.ConsoleRequestLogger;
import com.turbomanage.httpclient.HttpResponse;
import com.turbomanage.httpclient.RequestLogger;
import com.viacom.viacompt.R;

import java.io.IOException;
import java.net.HttpURLConnection;

import static com.viacom.viacompt.util.LogUtils.LOGD;
import static com.viacom.viacompt.util.LogUtils.LOGE;
import static com.viacom.viacompt.util.LogUtils.makeLogTag;

/**
 * Created by Shilpan Patel on 2/2/15.
 */
public class NetworkUtils {

    private static final String TAG = makeLogTag("NetworkUtils");

    // Request to GET
    public static String getDataFromUri(Context mContext, String url) throws IOException {

        if(!NetworkUtils.isConnected(mContext)) {
            LOGE(TAG, "No network connectivity.");
            return null;
        }

        BasicHttpClient httpClient = new BasicHttpClient();
        httpClient.setRequestLogger(mQuietLogger);

        HttpResponse response = httpClient.get(url, null);

        if (response == null) {
            LOGE(TAG, "Request returned null response.");
            throw new IOException("Request for data returned null response.");
        }

        int status = response.getStatus();

        if (status == HttpURLConnection.HTTP_OK) {
            LOGD(TAG, "Server returned HTTP_OK, so new data is available.");
            String body = response.getBodyAsString();
            if (TextUtils.isEmpty(body)) {
                LOGE(TAG, "Request for manifest returned empty data.");
                throw new IOException("Error fetching conference data manifest: no data.");
            }
            return body;
        } else if (status == HttpURLConnection.HTTP_NOT_MODIFIED) {
            // data on the server is not newer than our data
            LOGD(TAG, "HTTP_NOT_MODIFIED: data has not changed");
            return null;
        } else {
            LOGE(TAG, "Error fetching conference data: HTTP status " + status);
            throw new IOException("Error fetching conference data: HTTP status " + status);
        }

    }

    // Pop-Up dialog for network interface
    public static void showNoConnectionDialog(Context mContext) {
        final Context ctx = mContext;
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setMessage(R.string.no_connection);
        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ctx.startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                return;
            }
        });

        builder.show();
    }

    // A type of ConsoleRequestLogger that does not log requests and responses.
    private static RequestLogger mQuietLogger = new ConsoleRequestLogger(){
        @Override
        public void logRequest(HttpURLConnection uc, Object content) throws IOException { }

        @Override
        public void logResponse(HttpResponse res) { }
    };

    // Check for network connectivity
    public static boolean isConnected(Context context){
        NetworkInfo info = NetworkUtils.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    // Check if there is any connectivity to a Wifi network
    public static boolean isConnectedWifi(Context context){
        NetworkInfo info = NetworkUtils.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    // Check if there is any connectivity to a mobile network
    public static boolean isConnectedMobile(Context context){
        NetworkInfo info = NetworkUtils.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    // Get the network info
    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

}
