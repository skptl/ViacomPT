package com.viacom.viacompt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.viacom.viacompt.models.ApiResponse;
import com.viacom.viacompt.models.Records;
import com.viacom.viacompt.ui.VineListAdapter;
import com.viacom.viacompt.util.JsonUtils;
import com.viacom.viacompt.util.NetworkUtils;

import java.util.List;

import static com.viacom.viacompt.util.LogUtils.LOGD;
import static com.viacom.viacompt.util.LogUtils.makeLogTag;

/**
 * Created by Shilpan Patel on 1/28/15.
 */
public class ViacomMain extends ActionBarActivity {

    private static final String TAG = makeLogTag("ViacomMain");
    private Context mContext = ViacomMain.this;
    // UI components
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaicom_main);
        LOGD(TAG, "onCreate");

        // Binding UI components
        bindComponents();
        // Adding event listeners
        addListeners();
        // Fetch data
        AsyncTask<String, Void, String> execute = new HttpAsyncTask().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        LOGD(TAG, "onResume");
        if (!Config.isConnected(mContext)) {
            Toast.makeText(mContext, "No network connectivity", Toast.LENGTH_LONG).show();
            NetworkUtils.showNoConnectionDialog(mContext);
        }
    }

    private void bindComponents() {
        listView = (ListView) findViewById(R.id.viacom_main_list);
    }

    private void addListeners() {
        if (listView != null)
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Records record = (Records) parent.getItemAtPosition(position);
                        LOGD(TAG, record.getVideoDashUrl());

                        String URL = record.getVideoUrl();

                        if(Config.isConnectedMobile(mContext))
                            URL = record.getVideoLowURL();

                        // Start video
                        Intent videoIntent = new Intent(mContext, ViacomVideoActivity.class);
                        videoIntent.putExtra("videoString",URL);
                        startActivity(videoIntent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            LOGD(TAG, "onPreExecute");
            progressDialog = ProgressDialog.show(mContext, "", "Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            LOGD(TAG, "doInBackground");
            String jsonResponse = null;
            try {
                jsonResponse = NetworkUtils.getDataFromUri(mContext, Config.apiUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            LOGD(TAG, "onPostExecute");
            progressDialog.dismiss();
            ApiResponse apiResponse = JsonUtils.parseJsonToApiResponse(result);
            if(apiResponse.getSuccess()) {
                List<Records> records = apiResponse.getData().getRecords();
                VineListAdapter vineListAdapter = new VineListAdapter(mContext, records);
                listView.setAdapter(vineListAdapter);
            }
            else {
                Toast.makeText(mContext, apiResponse.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
