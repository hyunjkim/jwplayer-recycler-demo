package com.jwplayer.opensourcedemo.recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.jwplayer.opensourcedemo.R;
import com.jwplayer.opensourcedemo.Time;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

class JWViewHolder extends RecyclerView.ViewHolder{

    private View mRoot;
    private TextView title;
    private JWPlayerView jwPlayerView;

    public JWViewHolder(View itemView) {
        super(itemView);
        if(mRoot == null) mRoot = itemView;
        jwPlayerView = (JWPlayerView) mRoot.findViewById(R.id.jwplayer);
        title = (TextView) mRoot.findViewById(R.id.viewholder_text);
    }

    public void bind(MyJWList playlistItem, final int position) {
        Time.print("HYUNJOO: "+ String.valueOf(playlistItem));
        title.setText("A video");

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), JWPlayer.class);
                intent.putExtra("videoPosition", position);
                v.getContext().startActivity(intent);
            }
        });
    }
}
