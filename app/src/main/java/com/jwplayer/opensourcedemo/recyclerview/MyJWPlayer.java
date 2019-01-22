package com.jwplayer.opensourcedemo.recyclerview;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jwplayer.opensourcedemo.JWEventHandler;
import com.jwplayer.opensourcedemo.KeepScreenOnHandler;
import com.jwplayer.opensourcedemo.R;
import com.jwplayer.opensourcedemo.Time;
import com.jwplayer.opensourcedemo.pojo.JWMediaFiles;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.events.FullscreenEvent;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunjookim on 4/3/18.
 */

public class MyJWPlayer extends AppCompatActivity implements VideoPlayerEvents.OnFullscreenListener{

    private static final String MEASURE_TAG = "JWEVENTHANDLER";

    /**
     * Reference to the {@link com.longtailvideo.jwplayer.JWPlayerView}
     */
    private com.longtailvideo.jwplayer.JWPlayerView mPlayerView;

    /**
     * Stored instance of CoordinatorLayout
     * http://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.html
     */
    private List<JWMediaFiles> myList = new ArrayList<>();
    private CoordinatorLayout mCoordinatorLayout;
    private String videoURL, imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jwplayerview);

        mPlayerView = findViewById(R.id.jwplayer);
        mCoordinatorLayout = findViewById(R.id.activity_jwplayerview);
        TextView outputTextView = findViewById(R.id.output);
        ScrollView mScrollView = findViewById(R.id.scroll);

        String versionBuild = String.format("Build Version#: %s", mPlayerView.getVersionCode());
        outputTextView.setText(versionBuild);

        Intent getBundle = getIntent();

        print("HYUNJOO - video file: "+ getBundle.getStringExtra("videoFile"));
        videoURL = getBundle.getStringExtra("videoFile");
        imageURL = getBundle.getStringExtra("imageFile");

        setupView();

        mPlayerView.addOnFullscreenListener(this);
        new KeepScreenOnHandler(mPlayerView, getWindow());
        new JWEventHandler(mPlayerView, outputTextView, mScrollView);
    }

    private void setupView() {

        PlayerConfig playerConfig = new PlayerConfig.Builder()
                .file(videoURL)
                .image(imageURL)
                .autostart(true)
                .build();

        mPlayerView.setup(playerConfig);
    }

    public void print(String activity){
        Log.i(MEASURE_TAG,  Time.currentTime() + " on" + activity +" (\"JW\")");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Set fullscreen when the device is rotated to landscape
        mPlayerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE, true);
        super.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onResume() {
        // Let JW Player know that the app has returned from the background
        super.onResume();
        print("Resume");
        mPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        // Let JW Player know that the app is going to the background
        mPlayerView.onPause();
        print("Pause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // Let JW Player know that the app is being destroyed
        mPlayerView.onDestroy();
        print("Destroy");
        super.onDestroy();
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Exit fullscreen when the user pressed the Back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mPlayerView.getFullscreen()) {
				mPlayerView.setFullscreen(false, true);
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Handles JW Player going to and returning from fullscreen by hiding the ActionBar
	 *
	 * @param fullscreenEvent true if the player is fullscreen
	 */
	@Override
	public void onFullscreen(FullscreenEvent fullscreenEvent) {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			if (fullscreenEvent.getFullscreen()) {
				actionBar.hide();
				print("onFullscreen(): actionbar hide");
			} else {
				print("onFullscreen(): actionbar show");
				actionBar.show();
			}
		}

		// When going to Fullscreen we want to set fitsSystemWindows="false"
		mCoordinatorLayout.setFitsSystemWindows(!fullscreenEvent.getFullscreen());
	}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_jwplayerview, menu);
//        return true;
//    }

}
