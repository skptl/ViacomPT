package com.viacom.viacompt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import static com.viacom.viacompt.util.LogUtils.*;

public class ViacomMain extends Activity {

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
        listView = (ListView) findViewById(R.id.viacom_main_list);
    }

    private void addListeners() {
        if(listView!=null)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LOGD(TAG, "I am clicked : " + position);
            }
        });
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
            LOGD(TAG, "onPostExecute");
            progressDialog.dismiss();
            ApiResponse apiResponse = JsonUtils.parseJsonToApiResponse(result);
            List<Records> records = apiResponse.getData().getRecords();
            VineListAdapter vineListAdapter = new VineListAdapter(mContext, records);
            listView.setAdapter(vineListAdapter);
        }
    }
}
