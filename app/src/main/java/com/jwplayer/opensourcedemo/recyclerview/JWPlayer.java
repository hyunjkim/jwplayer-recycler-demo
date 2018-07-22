package com.jwplayer.opensourcedemo.recyclerview;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jwplayer.opensourcedemo.JWEventHandler;
import com.jwplayer.opensourcedemo.KeepScreenOnHandler;
import com.jwplayer.opensourcedemo.R;
import com.jwplayer.opensourcedemo.Time;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunjookim on 4/3/18.
 */

public class JWPlayer extends AppCompatActivity implements VideoPlayerEvents.OnFullscreenListener{

    private static final String MEASURE_TAG = "JWMeasure";

    /**
     * Reference to the {@link JWPlayerView}
     */
    private JWPlayerView mPlayerView;

    /**
     * An instance of our event handling class
     */
    private JWEventHandler mEventHandler;

    /**
     * Stored instance of CoordinatorLayout
     * http://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.html
     */
    private List<MyJWList> myList = new ArrayList<>();
    private TextView outputTextView;
    private ScrollView mScrollView;
    private CoordinatorLayout mCoordinatorLayout;
    private JWPlayerView mJWPlayerView;
    private String videoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jwplayerview);

        Bundle bundle = new Bundle();
        int videoPosition = bundle.getInt("videoPosition");

        //get the file video URL from the playlistitem
        videoURL = MyJWList.getPlaylistItem().get(videoPosition).getFile();

        mJWPlayerView = findViewById(R.id.jwplayer);
        mCoordinatorLayout = findViewById(R.id.activity_jwplayerview);
        outputTextView = findViewById(R.id.output);
        mScrollView = findViewById(R.id.scroll);

        new KeepScreenOnHandler(mPlayerView, getWindow());

        mEventHandler = new JWEventHandler(mPlayerView, outputTextView, mScrollView);

        setupView();
    }

    private void setupView() {

        PlayerConfig playerConfig = new PlayerConfig.Builder()
                .file(videoURL)
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
    protected void onStart() {
        super.onStart();
        print("Start");
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
    protected void onStop() {
        print("Stop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        print("Restart");
        super.onRestart();
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
	 * @param fullscreen true if the player is fullscreen
	 */
	@Override
	public void onFullscreen(boolean fullscreen) {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			if (fullscreen) {
				actionBar.hide();
			} else {
				actionBar.show();
			}
		}

		// When going to Fullscreen we want to set fitsSystemWindows="false"
		mCoordinatorLayout.setFitsSystemWindows(!fullscreen);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jwplayerview, menu);
        return true;
    }

}
