package com.jwplayer.opensourcedemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import com.jwplayer.opensourcedemo.recyclerview.JWAdapter;
import com.jwplayer.opensourcedemo.recyclerview.MyJWList;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.cast.CastManager;
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

        VMAPConfig();
        setupRecyclerView();
	}

    private void setupRecyclerView() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.jwplayer_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParent()));
        mRecyclerView.setAdapter(new JWAdapter(myList));
    }

    private void VMAPConfig(){
        List<PlaylistItem> playlist = new ArrayList<>();

        String vmapURL = "https://secure.adnxs.com/vmap?id=13028076&cb=CACHEBUSTER&aa-sch-country_code=no&aa-sch-publisher=vg&aa-sch-supply_type=app_phone_android&no-sno-adformat=postroll&no-sno-news-tv_category=1&no-sno-publishergroup=schibsted\n";
		String videoURL = "http://content.jwplatform.com/videos/kaUXWqTZ-640.mp4";

    	PlaylistItem playlistItem = new PlaylistItem(videoURL);
    	playlist.add(playlistItem);

    	myList.add(new MyJWList(playlist,vmapURL));
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
