package com.jwplayer.opensourcedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jwplayer.opensourcedemo.pojo.JWMediaFiles;
import com.jwplayer.opensourcedemo.recyclerview.JWAdapter;
import com.jwplayer.opensourcedemo.recyclerview.JWPlayer;

public class MainJWActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String MEASURE_TAG = "JWMeasure";
    private static JWMediaFiles jwMediaFiles;
    private RecyclerView mRecyclerView;
    private ImageView mImageView;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);

        mRecyclerView = findViewById(R.id.jwplayer_rv);
        mImageView = findViewById(R.id.imageview);

        // Create my playlist
        jwMediaFiles = JWMediaFiles.newInstance();

        // Pass the playlist to my recyclerview
        mainView();
        setupRecyclerView();

	}

    private void mainView() {
        Glide.with(this).load(jwMediaFiles.getMyPlaylist().get(0).getImage()).into(mImageView);
        mImageView.setOnClickListener(this);
    }

    // Set up my RecyclerView
    private void setupRecyclerView() {

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParent()));
        mRecyclerView.setAdapter(new JWAdapter(jwMediaFiles.getMyPlaylist()));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageview:
                Intent intent = new Intent(v.getContext(), JWPlayer.class);
                intent.putExtra("videoFile", jwMediaFiles.getMyPlaylist().get(0).getFile());
                intent.putExtra("imageFile", jwMediaFiles.getMyPlaylist().get(0).getImage());
                v.getContext().startActivity(intent);
                break;
            default:
                break;
        }
    }


    public void print(String activity){
		Log.i(MEASURE_TAG,  Time.currentTime() + "MainJWActivity: on" + activity +" (\"JW\")");
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
