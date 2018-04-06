package com.jwplayer.opensourcedemo.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jwplayer.opensourcedemo.R;
import com.longtailvideo.jwplayer.JWPlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunjookim on 4/3/18.
 */

public class JWAdapter extends RecyclerView.Adapter<JWViewHolder> {
    private List<MyJWList> mPlaylist = new ArrayList<>();
    private JWPlayerView mPlayerView;

    public JWAdapter(List<MyJWList> mPlaylist) {
        this.mPlaylist = mPlaylist;
        notifyDataSetChanged();
    }

    @Override
    public JWViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.jw_viewholder, parent, false);
        return new JWViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JWViewHolder holder, int position) {
        holder.bind(mPlaylist.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mPlaylist.size();
    }


}
