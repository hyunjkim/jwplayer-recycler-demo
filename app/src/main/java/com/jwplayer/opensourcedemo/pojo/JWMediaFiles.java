package com.jwplayer.opensourcedemo.pojo;

import com.longtailvideo.jwplayer.media.ads.AdBreak;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JWMediaFiles {

    private static JWMediaFiles mJWMediaFiles;

    private String[] myMediaIdList = {
            "g0rXSaaj",
            "q9myKOl4",
            "8hWfEzVj",
            "gWFXB2Sl",
            "40GXtX2m",
            "HUf7ubQI",
            "aF1ahOEy",
            "LRxkKHoQ",
            "pO9la63e",
            "bgz7BWYc",
            "jGTe03gS",
            "8uV7OAsP",
            "hFwv3DHe"
    };

    private String[] title = {
                    "Joy Ride",
                    "Blacksmith",
                    "Cycle Tour: Tuscany to Umbria",
                    "Dogsledding in Alaska",
                    "Friends with Sparklers",
                    "Road Trip",
                    "Driving USA",
                    "Slow Motion Sparks",
                    "Patagonia Mountain Cabin",
                    "Road Cycling Outdoors",
                    "Surfers at Sunrise",
                    "Daybreak",
                    "Surfing Ocean Wave"};

    private String imaTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpreonly&cmsid=496&vid=short_onecue&correlator=";

    private List<PlaylistItem> playlistItemList = new ArrayList<>();
    private Map<String, AdBreak> mMediaFile;

    /*
    * Make this once
    * */
    public JWMediaFiles() {
        passMyList();
    }

    /*
    * Singleton
    * */
    public static JWMediaFiles newInstance(){
        if(mJWMediaFiles == null) return new JWMediaFiles();
        else return mJWMediaFiles;
    }

    public void setJWMediaFile(Map<String, AdBreak> mediaIdList) {
        if (mediaIdList.size() == 0) passMyList();
        else {
            this.mMediaFile = mediaIdList;
            createMediaList();
        }
    }
    /**
     * Pass a default Playlistitem
     * */
    public List<PlaylistItem> passMyList(){
        for(int mediaId = 0; mediaId < myMediaIdList.length ; mediaId++){
            playlistItemList.add(new PlaylistItem.Builder()
                    .file("https://cdn.jwplayer.com/manifests/" + myMediaIdList[mediaId] + ".m3u8")
                    .title(title[mediaId])
                    .image("https://cdn.jwplayer.com/thumbs/" + myMediaIdList[mediaId] + "-720.jpg")
                    .mediaId(myMediaIdList[mediaId])
                    .build());
        }
        return playlistItemList;
    }

    /**
     * If a media list and ad schedule pass then create the list
     * */
    private void createMediaList(){

        PlaylistItem playlistItem;

        for(Map.Entry<String,AdBreak> mediaId: mMediaFile.entrySet()){
            playlistItem = new PlaylistItem.Builder()
                    .file("https://cdn.jwplayer.com/manifests/" + mediaId.getKey() + ".m3u8")
                    .image("https://cdn.jwplayer.com/thumbs/" + mediaId.getKey() + "-720.jpg")
                    .mediaId(mediaId.getKey())
                    .build();
            if(mediaId.getValue()!=null) {
                List<AdBreak> adBreakList = new ArrayList<>();
                adBreakList.add(mediaId.getValue());
                playlistItem.setAdSchedule(adBreakList);
            }
            playlistItemList.add(playlistItem);
        }
    }

    /**
     * Return whatever list was created
     */
    public List<PlaylistItem> getMyPlaylist(){
        return playlistItemList;
    }
}
