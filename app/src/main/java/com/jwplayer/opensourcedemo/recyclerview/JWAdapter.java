package com.jwplayer.opensourcedemo.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jwplayer.opensourcedemo.R;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunjookim on 4/3/18.
 */

public class JWAdapter extends RecyclerView.Adapter<JWViewHolder> {
    private List<PlaylistItem> mPlaylist;

    public JWAdapter(List<PlaylistItem> mPlaylist) {
        this.mPlaylist = mPlaylist;
        notifyDataSetChanged();
    }

    /*
    * Reusing this cardview
    * */
    @NonNull
    @Override
    public JWViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.jw_viewholder, parent, false);
        return new JWViewHolder(view);
    }

    /**
     * Connecting each playlist item to my view
     */
    @Override
    public void onBindViewHolder(@NonNull JWViewHolder holder, int position) {
        holder.bind(mPlaylist.get(position), position);
    }

    /*
    * Size of the playlist I am using
    * */
    @Override
    public int getItemCount() {
        return mPlaylist.size();
    }
}
