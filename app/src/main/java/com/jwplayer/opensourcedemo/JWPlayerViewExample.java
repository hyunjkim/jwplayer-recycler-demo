package com.jwplayer.opensourcedemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jwplayer.opensourcedemo.recyclerview.JWAdapter;
import com.jwplayer.opensourcedemo.recyclerview.MyJWList;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.media.ads.Ad;
import com.longtailvideo.jwplayer.media.ads.AdBreak;
import com.longtailvideo.jwplayer.media.ads.AdSource;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.ArrayList;
import java.util.List;

public class JWPlayerViewExample extends AppCompatActivity{

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
	private CoordinatorLayout mCoordinatorLayout;

    private List<MyJWList> myList = new ArrayList<>();


    @Override
	protected void onCreate(Bundle savedInstanceState) {
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);

        // Create playlist and add to my list
        configPlaylist();

        // Pass the playlist to my recyclerview
        setupRecyclerView();

	}

    private void setupRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.jwplayer_rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParent()));
        mRecyclerView.setAdapter(new JWAdapter(myList));
    }

    /*
    * Add one video and click on one video
    * */

    private void configPlaylist(){
        List<PlaylistItem> playlist = new ArrayList<>();

        String imaTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpreonly&cmsid=496&vid=short_onecue&correlator=";
		String videoURL = "http://content.jwplatform.com/videos/kaUXWqTZ-640.mp4";

		Ad ad = new Ad(AdSource.IMA, imaTag);
		List<AdBreak> adBreakList = new ArrayList<>();
		AdBreak adBreak = new AdBreak("pre",ad);
		adBreakList.add(adBreak);

    	PlaylistItem playlistItem = new PlaylistItem(videoURL);
    	playlistItem.setAdSchedule(adBreakList);
    	playlist.add(playlistItem);

    	myList.add(new MyJWList(playlist));
	}

    public void print(String activity){
		Log.i(MEASURE_TAG,  Time.currentTime() + "JWPlayerViewExample: on" + activity +" (\"JW\")");
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
	}

	@Override
	protected void onPause() {
		// Let JW Player know that the app is going to the background
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
        print("Destroy");
        super.onDestroy();
    }

}
