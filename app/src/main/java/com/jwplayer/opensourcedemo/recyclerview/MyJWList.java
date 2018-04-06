package com.jwplayer.opensourcedemo.recyclerview;

import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.List;

/**
 * Created by hyunjookim on 4/3/18.
 */

public class MyJWList {
    List<PlaylistItem> playlistItem;
    String vmapURL;

    public MyJWList() {

    }

    public MyJWList(List<PlaylistItem> playlist, String vmapURL) {
        playlistItem = playlist;
        this.vmapURL = vmapURL;
    }

    public List<PlaylistItem> getPlaylistItem() {
        return playlistItem;
    }

    public String getVmapURL() {
        return vmapURL;
    }

}
