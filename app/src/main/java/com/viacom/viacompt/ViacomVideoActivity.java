package com.viacom.viacompt;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.viacom.viacompt.util.NetworkUtils;

import static com.viacom.viacompt.util.LogUtils.LOGD;
import static com.viacom.viacompt.util.LogUtils.makeLogTag;

/**
 * Created by Shilpan Patel on 2/2/15.
 */
public class ViacomVideoActivity extends ActionBarActivity {

    private static final String TAG = makeLogTag("ViacomVideoActivity");
    private Context mContext = ViacomVideoActivity.this;
    private VideoView videoView;
    private MediaController videoControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_playback);

        LOGD(TAG, "onCreate");

        if(NetworkUtils.isConnectedMobile(mContext))
            Toast.makeText(mContext, "Data charges may apply.", Toast.LENGTH_LONG).show();

        String videoString=getIntent().getStringExtra("videoString");
        Uri videoUri = Uri.parse(videoString);
        // Video surface
        videoView = (VideoView)findViewById(R.id.videoView);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        // Media Controls
        videoControl = new MediaController(this);
        videoControl.setAnchorView(videoView);
        videoView.setMediaController(videoControl);

    }

    @Override
    protected  void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LOGD(TAG, "onResume");
        if (!NetworkUtils.isConnected(mContext)) {
            Toast.makeText(mContext, "No network connectivity", Toast.LENGTH_LONG).show();
            NetworkUtils.showNoConnectionDialog(mContext);
        }
        videoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView = null;
        videoControl = null;
    }
}
