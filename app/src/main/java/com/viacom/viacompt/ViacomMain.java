package com.viacom.viacompt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.viacom.viacompt.models.ApiResponse;
import com.viacom.viacompt.models.Record;
import com.viacom.viacompt.util.JsonUtils;
import com.viacom.viacompt.util.NetworkUtils;

import java.util.ArrayList;

import static com.viacom.viacompt.util.LogUtils.LOGD;
import static com.viacom.viacompt.util.LogUtils.makeLogTag;

public class ViacomMain extends Activity {

    private static final String TAG = makeLogTag("ViacomMain");
    private Context mContext = ViacomMain.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaicom_main);
        LOGD(TAG, "onCreate");


        //bindComponents();
        //addListeners();
        AsyncTask<String, Void, String> execute = new HttpAsyncTask().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        LOGD(TAG, "onResume");
        if(!Config.isConnected(mContext)) {
            Toast.makeText(mContext, "No network connectivity", Toast.LENGTH_LONG).show();
            NetworkUtils.showNoConnectionDialog(mContext);
        }
    }

    private void bindComponents() {

    }

    private void addListeners() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vaicom_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        private ProgressDialog progressDialog;
        
        @Override
        protected void onPreExecute() {
            LOGD(TAG, "onPreExecute");
            progressDialog = ProgressDialog.show(mContext, "", "Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            LOGD(TAG, "doInBackground");

            try {
                String jsonResponse = NetworkUtils.getDataFromUri(mContext, Config.apiUrl);
                ApiResponse apiResponse = JsonUtils.parseJsonToApiResponse(jsonResponse);

                ArrayList<Record> records = apiResponse.getData().getRecords();

                LOGD(TAG,"API Parsed : " + records.get(0).getAvatarUrl());

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            LOGD(TAG, "onPostExecute");
            progressDialog.dismiss();
        }
    }
}
