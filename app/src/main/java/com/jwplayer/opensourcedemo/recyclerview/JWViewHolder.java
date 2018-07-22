package com.jwplayer.opensourcedemo.recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jwplayer.opensourcedemo.R;
import com.jwplayer.opensourcedemo.Time;

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

    public void bind(MyJWList playlistItem, final int position) {
        Time.print("HYUNJOO: "+ String.valueOf(playlistItem) + "position:" + position);
        mTitle.setText("A video");

        mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), JWPlayer.class);
                intent.putExtra("videoPosition", position);
                v.getContext().startActivity(intent);
            }
        });
    }
}
