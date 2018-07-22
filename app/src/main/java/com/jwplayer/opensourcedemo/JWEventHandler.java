package com.jwplayer.opensourcedemo;

import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.core.PlayerState;
import com.longtailvideo.jwplayer.events.AdCompanionEvent;
import com.longtailvideo.jwplayer.events.AdCompleteEvent;
import com.longtailvideo.jwplayer.events.AdImpressionEvent;
import com.longtailvideo.jwplayer.events.AdPauseEvent;
import com.longtailvideo.jwplayer.events.AdPlayEvent;
import com.longtailvideo.jwplayer.events.AdRequestEvent;
import com.longtailvideo.jwplayer.events.AdScheduleEvent;
import com.longtailvideo.jwplayer.events.AdSkippedEvent;
import com.longtailvideo.jwplayer.events.AdTimeEvent;
import com.longtailvideo.jwplayer.events.BufferChangeEvent;
import com.longtailvideo.jwplayer.events.ControlBarVisibilityEvent;
import com.longtailvideo.jwplayer.events.ControlsEvent;
import com.longtailvideo.jwplayer.events.ErrorEvent;
import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.adaptive.QualityLevel;
import com.longtailvideo.jwplayer.media.adaptive.VisualQuality;
import com.longtailvideo.jwplayer.media.audio.AudioTrack;
import com.longtailvideo.jwplayer.media.captions.Caption;
import com.longtailvideo.jwplayer.media.meta.Metadata;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import java.util.List;

/**
 * Outputs allEventHandler: Player Events to logging, with the exception of time events.
 */
public class JWEventHandler implements
        AdvertisingEvents.OnAdCompanionsListener,
        AdvertisingEvents.OnAdCompleteListenerV2,
        AdvertisingEvents.OnAdErrorListener,
        AdvertisingEvents.OnAdImpressionListenerV2,
        AdvertisingEvents.OnAdPauseListenerV2,
        AdvertisingEvents.OnAdPlayListenerV2,
        AdvertisingEvents.OnAdRequestListenerV2,
        AdvertisingEvents.OnAdScheduleListener,
        AdvertisingEvents.OnAdSkippedListenerV2,
        AdvertisingEvents.OnAdStartedListener,
        AdvertisingEvents.OnAdTimeListenerV2,
        AdvertisingEvents.OnBeforeCompleteListener,
        AdvertisingEvents.OnBeforePlayListener,
        VideoPlayerEvents.OnAudioTrackChangedListener,
        VideoPlayerEvents.OnAudioTracksListener,
        VideoPlayerEvents.OnBufferChangeListener,
        VideoPlayerEvents.OnBufferListener,
        VideoPlayerEvents.OnCaptionsChangedListener,
        VideoPlayerEvents.OnCaptionsListListener,
        VideoPlayerEvents.OnCompleteListener,
        VideoPlayerEvents.OnControlBarVisibilityListener,
        VideoPlayerEvents.OnControlsListener,
        VideoPlayerEvents.OnDisplayClickListener,
        VideoPlayerEvents.OnErrorListenerV2,
        VideoPlayerEvents.OnFirstFrameListener,
        VideoPlayerEvents.OnFullscreenListener,
        VideoPlayerEvents.OnIdleListener,
        VideoPlayerEvents.OnLevelsChangedListener,
        VideoPlayerEvents.OnLevelsListener,
        VideoPlayerEvents.OnMetaListener,
        VideoPlayerEvents.OnMuteListener,
        VideoPlayerEvents.OnPauseListener,
        VideoPlayerEvents.OnPlaylistCompleteListener,
        VideoPlayerEvents.OnPlayListener,
        VideoPlayerEvents.OnPlaylistItemListener,
        VideoPlayerEvents.OnPlaylistListener,
        VideoPlayerEvents.OnSeekedListener,
        VideoPlayerEvents.OnSeekListener,
        VideoPlayerEvents.OnSetupErrorListener,
        VideoPlayerEvents.OnTimeListener,
        VideoPlayerEvents.OnVisualQualityListener {

    private static final String MEASURE_TAG = "JWMeasure";
    private JWPlayerView jwPlayerView;
    private final TextView mOutput;
    private final StringBuilder outputStringBuilder = new StringBuilder();
    private final ScrollView mScroll;

    public JWEventHandler(JWPlayerView jwPlayerView, TextView output, ScrollView scrollview) {
        this.jwPlayerView = jwPlayerView;
        mScroll = scrollview;
        mOutput = output;

        // Subscribe to allEventHandler: Player events
        jwPlayerView.addOnAdCompanionsListener(this);
        jwPlayerView.addOnAdCompleteListener(this);
        jwPlayerView.addOnAdErrorListener(this);
        jwPlayerView.addOnAdImpressionListener(this);
        jwPlayerView.addOnAdPauseListener(this);
        jwPlayerView.addOnAdPlayListener(this);
        jwPlayerView.addOnAdRequestListener(this);
        jwPlayerView.addOnAdScheduleListener(this);
        jwPlayerView.addOnAdSkippedListener(this);
        jwPlayerView.addOnAdStartedListener(this);
        jwPlayerView.addOnAdTimeListener(this);
        jwPlayerView.addOnBeforeCompleteListener(this);
        jwPlayerView.addOnBeforePlayListener(this);
        jwPlayerView.addOnAudioTrackChangedListener(this);
        jwPlayerView.addOnAudioTracksListener(this);
        jwPlayerView.addOnBufferChangeListener(this);
        jwPlayerView.addOnBufferListener(this);
        jwPlayerView.addOnCaptionsChangedListener(this);
        jwPlayerView.addOnCaptionsListListener(this);
        jwPlayerView.addOnCompleteListener(this);
        jwPlayerView.addOnControlBarVisibilityListener(this);
        jwPlayerView.addOnControlsListener(this);
        jwPlayerView.addOnDisplayClickListener(this);
        jwPlayerView.addOnErrorListener(this);
        jwPlayerView.addOnFirstFrameListener(this);
        jwPlayerView.addOnFullscreenListener(this);
        jwPlayerView.addOnIdleListener(this);
        jwPlayerView.addOnLevelsChangedListener(this);
        jwPlayerView.addOnLevelsListener(this);
        jwPlayerView.addOnMetaListener(this);
        jwPlayerView.addOnMuteListener(this);
        jwPlayerView.addOnPauseListener(this);
        jwPlayerView.addOnPlaylistCompleteListener(this);
        jwPlayerView.addOnPlayListener(this);
        jwPlayerView.addOnPlaylistItemListener(this);
        jwPlayerView.addOnPlaylistListener(this);
        jwPlayerView.addOnSeekedListener(this);
        jwPlayerView.addOnSeekListener(this);
        jwPlayerView.addOnSetupErrorListener(this);
        jwPlayerView.addOnTimeListener(this);
        jwPlayerView.addOnVisualQualityListener(this);
    }

    private void updateOutput(String output) {
        outputStringBuilder.append(""  + Time.currentTime() + " " + output).append("\r\n");
        mOutput.setText(outputStringBuilder.toString());
        mScroll.scrollTo(0,mOutput.getBottom());
    }

    public void print(String log){
        Log.i(MEASURE_TAG,"" + Time.currentTime() + log + "(Event)");
    }

    @Override
    public void onAdCompanions(AdCompanionEvent adCompanionEvent) {
        print(" onAdCompanions:  " + adCompanionEvent.getTag());

    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        print(" onAdComplete:  " + adCompleteEvent.getTag());

    }

    @Override
    public void onAdError(String s, String s1) {
        print(" getVmapInfo:  " + s + " " + s1 );

    }

    @Override
    public void onAdImpression(AdImpressionEvent adImpressionEvent) {
        print(" onAdImpression:  " + " \r\n"
                + " AdTitle: " + adImpressionEvent.getAdTitle() + "\r\n"
                + " MediaFile: " + adImpressionEvent.getMediaFile() + " \r\n"
                + " Vmap Info: " + adImpressionEvent.getVmapInfo());
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        print(" onAdPlay:  " + adPauseEvent.getTag());

    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        print(" onAdPlay:  " + adPlayEvent.getTag());

    }

    @Override
    public void onAdRequest(AdRequestEvent adRequestEvent) {
        print(" onAdRequest:  " + " \r\n"
                + " Pos: " + adRequestEvent.getAdPosition() + "\r\n"
                + " Tag: " + adRequestEvent.getTag() + " \r\n"
                + " Offset: " + adRequestEvent.getOffset());

    }

    @Override
    public void onAdSchedule(AdScheduleEvent adScheduleEvent) {
        print(" adSkippedEvent:  " + " \r\n"
                + " client: " + adScheduleEvent.getClient() + "\r\n"
                + " tag: " + adScheduleEvent.getTag());

    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        print(" onAdSkipped:  " + adSkippedEvent.getTag() );

    }

    @Override
    public void onAdStarted(String s, String s1) {
        print(" onAdStarted:  " +  s + " " + s1);

    }

    @Override
    public void onAdTime(AdTimeEvent adTimeEvent) {
        print(" onAdTime:  " + " \r\n"
            + " client: " + adTimeEvent.getClient() + "\r\n"
            + " Tag: " + adTimeEvent.getTag() + " \r\n"
            + " Pos: " + adTimeEvent.getPosition() + " \r\n"
            + " Dur: " + adTimeEvent.getDuration());

    }

    @Override
    public void onBeforeComplete() {
        print(" onBeforeComplete");

    }

    @Override
    public void onBeforePlay() {
        print(" onBeforePlay");

    }

    @Override
    public void onBufferChange(BufferChangeEvent bufferChangeEvent) {
        updateOutput(" " + "onBufferChangeEvent");
        print(" onBufferChange; percent=" + bufferChangeEvent.getBufferPercent() +
                " position=" + bufferChangeEvent.getPosition() +
                " duration=" + bufferChangeEvent.getDuration()
        );
    }

    @Override
    public void onAudioTrackChanged(int i) {
        print(" onAudioTrackChanged:  " + i);

    }

    @Override
    public void onAudioTracks(List<AudioTrack> list) {
        print(" onAudioTracks:  " + list.size());

    }

    @Override
    public void onBuffer(PlayerState playerState) {
        print(" onBuffer:  " + playerState);

    }

    @Override
    public void onCaptionsChanged(int i, List<Caption> list) {
        print(" onCaptionsChanged:  " + list.size());

    }

    @Override
    public void onCaptionsList(List<Caption> list) {
        print(" onCaptionsList:  " + list.size());

    }

    @Override
    public void onComplete() {
        print(" onComplete" );

    }

    @Override
    public void onControlBarVisibilityChanged(ControlBarVisibilityEvent controlBarVisibilityEvent) {
        print(" onControlBarVisibilityChanged:  " + controlBarVisibilityEvent.isVisible());

    }

    @Override
    public void onControls(ControlsEvent controlsEvent) {
        print(" onControls:  " + controlsEvent.getControls());

    }

    @Override
    public void onDisplayClick() {
        print(" onDisplayClick" );

    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        Exception exception = errorEvent.getException();
        Log.i(MEASURE_TAG, "onError: " + errorEvent.getMessage(), exception);
    }


    @Override
    public void onFirstFrame(int i) {
        print(" onFirstFrame:  " + i);

    }

    @Override
    public void onFullscreen(boolean b) {
        print(" onFullscreen:  " + b);

    }

    @Override
    public void onIdle(PlayerState playerState) {
        print(" onIdle:  " + playerState);

    }

    @Override
    public void onLevelsChanged(int i) {
        print(" onLevelsChanged:  " + i);

    }

    @Override
    public void onLevels(List<QualityLevel> list) {
        print(" onLevels:  " + list.size());

    }

    @Override
    public void onMeta(Metadata metadata) {
        print(" onMeta:  " + metadata);

    }

    @Override
    public void onMute(boolean b) {
        print(" onMute:  " + b);

    }

    @Override
    public void onPause(PlayerState playerState) {
        print(" onPause:  " + playerState);

    }

    @Override
    public void onPlay(PlayerState playerState) {
        print(" onPlay:  " + playerState);

    }

    @Override
    public void onPlaylistComplete() {
        print(" onPlaylistComplete!!" );

    }

    @Override
    public void onPlaylistItem(int i, PlaylistItem playlistItem) {
        print(" onPlaylistItem:  " + i + " \r\n "
                + " file: " + playlistItem.getFile() + "\r\n"
                + " mediaid: " + playlistItem.getMediaId() + "\r\n"
                + " mediaDRM:  " + playlistItem.getMediaDrmCallback());

    }

    @Override
    public void onPlaylist(List<PlaylistItem> list) {
        print(" onPlaylist:  " + list.size());

    }

    @Override
    public void onSeek(int i, int i1) {
        print(" onSeek:  " + i + " " + i1);

    }

    @Override
    public void onSeeked() {
        print(" onSeeked");

    }

    @Override
    public void onSetupError(String s) {
        print(" onSetupError:  " + s);

    }

    @Override
    public void onTime(long l, long l1) {
        print(" onTime:  " + l + " " + l1 );

    }

    @Override
    public void onVisualQuality(VisualQuality visualQuality) {
        print(" onVisualQuality:  " +visualQuality.getQualityLevel() );

    }
}
