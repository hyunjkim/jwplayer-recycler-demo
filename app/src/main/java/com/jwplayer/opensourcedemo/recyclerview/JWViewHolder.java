package com.jwplayer.opensourcedemo.recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jwplayer.opensourcedemo.R;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

class JWViewHolder extends RecyclerView.ViewHolder {

    private View mRoot;
    private TextView mTitle;
    private ImageView mImageView;


    public JWViewHolder(View itemView) {
        super(itemView);
        if(mRoot == null) mRoot = itemView;
        mImageView = mRoot.findViewById(R.id.viewholder_image);
        mTitle = mRoot.findViewById(R.id.viewholder_text);
    }

    public void bind(final PlaylistItem playlistItem, final int position) {
        final String image = playlistItem.getImage();
        mTitle.setText(playlistItem.getTitle());
        Glide.with(mRoot).load(image).into(mImageView);


        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MyJWPlayerFragment.class);
            intent.putExtra("videoFile", playlistItem.getFile());
            intent.putExtra("imageFile", image);
            v.getContext().startActivity(intent);
        });
    }
}
