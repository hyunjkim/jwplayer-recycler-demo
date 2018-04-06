package com.jwplayer.opensourcedemo.recyclerview;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.longtailvideo.jwplayer.cast.CastManager;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.ads.AdSource;
import com.longtailvideo.jwplayer.media.ads.VMAPAdvertising;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

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
    private ScrollView sv;
    private CoordinatorLayout mCoordinatorLayout;
    private CastManager mCastManager;
    String videoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jwplayerview);



        PlaylistItem playlistItem = (PlaylistItem) getIntent().getSerializableExtra("play");
//        int num = bundle.getInt("videoPosition");

//        Time.print(String.valueOf(jwList)+" "+k);
        videoURL = playlistItem.getFile();

        mCoordinatorLayout = (CoordinatorLayout)  findViewById(R.id.activity_jwplayerview);
        outputTextView = (TextView) findViewById(R.id.output);
        sv = (ScrollView)  findViewById(R.id.scroll);
        sv.setOverScrollMode(0);

        VMAPConfig();

        new KeepScreenOnHandler(mPlayerView, getWindow());

        mEventHandler = new JWEventHandler(mPlayerView, outputTextView, sv);
        mCastManager = CastManager.getInstance();
    }

    private void VMAPConfig(){
        List<PlaylistItem> playlist = new ArrayList<>();

        String vmapURL = "https://secure.adnxs.com/vmap?id=13028076&cb=CACHEBUSTER&aa-sch-country_code=no&aa-sch-publisher=vg&aa-sch-supply_type=app_phone_android&no-sno-adformat=postroll&no-sno-news-tv_category=1&no-sno-publishergroup=schibsted\n";

        PlaylistItem playlistItem = new PlaylistItem(videoURL);
        playlist.add(playlistItem);

        myList.add(new MyJWList(playlist,vmapURL));

		VMAPAdvertising vmapAdvertising = new VMAPAdvertising(AdSource.VAST,vmapURL);

		PlayerConfig playerConfig = new PlayerConfig.Builder()
				.playlist(playlist)
				.advertising(vmapAdvertising)
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
        // Register the MediaRouterButton on the JW Player SDK
        mCastManager.addMediaRouterButton(menu, R.id.media_route_menu_item);
        return true;
    }

}
