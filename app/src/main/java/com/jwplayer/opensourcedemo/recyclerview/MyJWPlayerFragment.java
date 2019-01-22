package com.jwplayer.opensourcedemo.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jwplayer.opensourcedemo.R;
import com.jwplayer.opensourcedemo.Time;
import com.jwplayer.opensourcedemo.handlers.JWEventHandler;
import com.jwplayer.opensourcedemo.handlers.KeepScreenOnHandler;
import com.longtailvideo.jwplayer.JWPlayerSupportFragment;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;

public class MyJWPlayerFragment  extends AppCompatActivity {

    /**
     * A reference to the {@link JWPlayerSupportFragment}.
     */
    private JWPlayerSupportFragment mPlayerFragment;

    /**
     * A reference to the {@link JWPlayerView} used by the JWPlayerSupportFragment.
     */
    private JWPlayerView mPlayerView;

    private static final String MEASURE_TAG = "JWEVENTHANDLER";
    private TextView outputTextView;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jwplayerfragment);

        outputTextView = findViewById(R.id.output);
        scrollView = findViewById(R.id.scroll);

        setupJWPlayer();
        setupHandler();
        printPlayerVersion();
    }

    private void setupHandler() {
        // Keep the screen on during playback
        new KeepScreenOnHandler(mPlayerView, getWindow());

        // Instantiate the JW Player event handler class
        new JWEventHandler(mPlayerView, outputTextView, scrollView);
    }

    private void printPlayerVersion() {
        String versionBuild = String.format("Build Version#: %s", mPlayerView.getVersionCode());
        outputTextView.setText(versionBuild);
    }

    private void setupJWPlayer() {
        Intent getBundle = getIntent();

        print("HYUNJOO - video file: "+ getBundle.getStringExtra("videoFile"));
        String videoURL = getBundle.getStringExtra("videoFile");
        String imageURL = getBundle.getStringExtra("imageFile");

        PlayerConfig playerConfig = new PlayerConfig.Builder()
                .file(videoURL)
                .image(imageURL)
                .autostart(true)
                .build();

        // Construct a new JWPlayerSupportFragment (since we're using AppCompatActivity)
        mPlayerFragment = JWPlayerSupportFragment.newInstance(playerConfig);

        // Attach the Fragment to our layout
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, mPlayerFragment);
        ft.commit();

        // Make sure all the pending fragment transactions have been completed, otherwise
        // mPlayerFragment.getPlayer() may return null
        fm.executePendingTransactions();

        // Get a reference to the JWPlayerView from the fragment
        mPlayerView = mPlayerFragment.getPlayer();
    }

    public void print(String activity){
        Log.i(MEASURE_TAG,  Time.currentTime() + " on" + activity +" (\"JW\")");
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
}