package com.jwplayer.opensourcedemo.recyclerview;

import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.List;

/**
 * Created by hyunjookim on 4/3/18.
 */

public class MyJWList {
    public static List<PlaylistItem> playlistItem;

    public MyJWList(List<PlaylistItem> playlist) {
        playlistItem = playlist;
    }

    public static List<PlaylistItem> getPlaylistItem() {
        return playlistItem;
    }

}
