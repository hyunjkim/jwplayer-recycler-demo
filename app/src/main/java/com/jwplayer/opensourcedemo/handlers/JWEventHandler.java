package com.jwplayer.opensourcedemo.handlers;

import android.os.Build;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jwplayer.opensourcedemo.Time;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.AdBreakEndEvent;
import com.longtailvideo.jwplayer.events.AdBreakStartEvent;
import com.longtailvideo.jwplayer.events.AdClickEvent;
import com.longtailvideo.jwplayer.events.AdCompanionsEvent;
import com.longtailvideo.jwplayer.events.AdCompleteEvent;
import com.longtailvideo.jwplayer.events.AdErrorEvent;
import com.longtailvideo.jwplayer.events.AdImpressionEvent;
import com.longtailvideo.jwplayer.events.AdPauseEvent;
import com.longtailvideo.jwplayer.events.AdPlayEvent;
import com.longtailvideo.jwplayer.events.AdRequestEvent;
import com.longtailvideo.jwplayer.events.AdScheduleEvent;
import com.longtailvideo.jwplayer.events.AdSkippedEvent;
import com.longtailvideo.jwplayer.events.AdStartedEvent;
import com.longtailvideo.jwplayer.events.AdTimeEvent;
import com.longtailvideo.jwplayer.events.AudioTrackChangedEvent;
import com.longtailvideo.jwplayer.events.AudioTracksEvent;
import com.longtailvideo.jwplayer.events.BeforeCompleteEvent;
import com.longtailvideo.jwplayer.events.BeforePlayEvent;
import com.longtailvideo.jwplayer.events.BufferChangeEvent;
import com.longtailvideo.jwplayer.events.BufferEvent;
import com.longtailvideo.jwplayer.events.CaptionsChangedEvent;
import com.longtailvideo.jwplayer.events.CaptionsListEvent;
import com.longtailvideo.jwplayer.events.CompleteEvent;
import com.longtailvideo.jwplayer.events.ControlBarVisibilityEvent;
import com.longtailvideo.jwplayer.events.ControlsEvent;
import com.longtailvideo.jwplayer.events.DisplayClickEvent;
import com.longtailvideo.jwplayer.events.ErrorEvent;
import com.longtailvideo.jwplayer.events.FirstFrameEvent;
import com.longtailvideo.jwplayer.events.FullscreenEvent;
import com.longtailvideo.jwplayer.events.IdleEvent;
import com.longtailvideo.jwplayer.events.LevelsChangedEvent;
import com.longtailvideo.jwplayer.events.LevelsEvent;
import com.longtailvideo.jwplayer.events.MetaEvent;
import com.longtailvideo.jwplayer.events.MuteEvent;
import com.longtailvideo.jwplayer.events.PauseEvent;
import com.longtailvideo.jwplayer.events.PlayEvent;
import com.longtailvideo.jwplayer.events.PlaylistCompleteEvent;
import com.longtailvideo.jwplayer.events.PlaylistEvent;
import com.longtailvideo.jwplayer.events.PlaylistItemEvent;
import com.longtailvideo.jwplayer.events.ReadyEvent;
import com.longtailvideo.jwplayer.events.SeekEvent;
import com.longtailvideo.jwplayer.events.SeekedEvent;
import com.longtailvideo.jwplayer.events.SetupErrorEvent;
import com.longtailvideo.jwplayer.events.TimeEvent;
import com.longtailvideo.jwplayer.events.VisualQualityEvent;
import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

/**
 * Outputs allEventHandler: Player Events to logging, with the exception of time events.
 */
public class JWEventHandler implements
        AdvertisingEvents.OnAdBreakEndListener,
        AdvertisingEvents.OnAdBreakStartListener,
        AdvertisingEvents.OnAdClickListener,
        AdvertisingEvents.OnAdCompanionsListener,
        AdvertisingEvents.OnAdCompleteListener,
        AdvertisingEvents.OnAdErrorListener,
        AdvertisingEvents.OnAdImpressionListener,
        AdvertisingEvents.OnAdPauseListener,
        AdvertisingEvents.OnAdPlayListener,
        AdvertisingEvents.OnAdRequestListener,
        AdvertisingEvents.OnAdScheduleListener,
        AdvertisingEvents.OnAdSkippedListener,
        AdvertisingEvents.OnAdStartedListener,
        AdvertisingEvents.OnAdTimeListener,
        AdvertisingEvents.OnBeforeCompleteListener,
        AdvertisingEvents.OnBeforePlayListener,

//        RelatedPluginEvents.OnRelatedCloseListener,
//        RelatedPluginEvents.OnRelatedOpenListener,
//        RelatedPluginEvents.OnRelatedPlayListener,
//
//        SharingPluginEvents.OnSharingClickListener,
//        SharingPluginEvents.OnSharingCloseListener,
//        SharingPluginEvents.OnSharingOpenListener,

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
        VideoPlayerEvents.OnErrorListener,
        VideoPlayerEvents.OnFirstFrameListener,
        VideoPlayerEvents.OnFullscreenListener,
        VideoPlayerEvents.OnIdleListener,
        VideoPlayerEvents.OnLevelsChangedListener,
        VideoPlayerEvents.OnLevelsListener,
        VideoPlayerEvents.OnMetaListener,
        VideoPlayerEvents.OnMuteListener,
        VideoPlayerEvents.OnPauseListener,
        VideoPlayerEvents.OnPlaybackRateChangedListener,
        VideoPlayerEvents.OnPlaylistCompleteListener,
        VideoPlayerEvents.OnPlayListener,
        VideoPlayerEvents.OnPlaylistItemListener,
        VideoPlayerEvents.OnPlaylistListener,
        VideoPlayerEvents.OnReadyListener,
        VideoPlayerEvents.OnSeekedListener,
        VideoPlayerEvents.OnSeekListener,
        VideoPlayerEvents.OnSetupErrorListener,
        VideoPlayerEvents.OnTimeListener,
        VideoPlayerEvents.OnVisualQualityListener{

    private static final String MEASURE_TAG = "JWMeasure";
    private final TextView mOutput;
    private final StringBuilder outputStringBuilder = new StringBuilder();
    private final ScrollView mScroll;

    public JWEventHandler(JWPlayerView mjwPlayerView, TextView output, ScrollView scrollview) {
        mScroll = scrollview;
        mOutput = output;

        // Subscribe to allEventHandler: Player events
        mjwPlayerView.addOnAdBreakEndListener(this);
        mjwPlayerView.addOnAdBreakStartListener(this);
        mjwPlayerView.addOnAdClickListener(this);
        mjwPlayerView.addOnAdCompanionsListener(this);
        mjwPlayerView.addOnAdCompleteListener(this);
        mjwPlayerView.addOnAdErrorListener(this);
        mjwPlayerView.addOnAdImpressionListener(this);
        mjwPlayerView.addOnAdPauseListener(this);
        mjwPlayerView.addOnAdPlayListener(this);
        mjwPlayerView.addOnAdRequestListener(this);
        mjwPlayerView.addOnAdScheduleListener(this);
        mjwPlayerView.addOnAdSkippedListener(this);
        mjwPlayerView.addOnAdStartedListener(this);
        mjwPlayerView.addOnAdTimeListener(this);
        mjwPlayerView.addOnBeforeCompleteListener(this);
        mjwPlayerView.addOnBeforePlayListener(this);

//        mjwPlayerView.addOnRelatedCloseListener(this);
//        mjwPlayerView.addOnRelatedOpenListener(this);
//        mjwPlayerView.addOnRelatedPlayListener(this);
//
//        mjwPlayerView.addOnSharingClickListener(this);
//        mjwPlayerView.addOnSharingCloseListener(this);
//        mjwPlayerView.addOnSharingOpenListener(this);

        mjwPlayerView.addOnAudioTrackChangedListener(this);
        mjwPlayerView.addOnAudioTracksListener(this);
        mjwPlayerView.addOnBufferChangeListener(this);
        mjwPlayerView.addOnBufferListener(this);
        mjwPlayerView.addOnCaptionsChangedListener(this);
        mjwPlayerView.addOnCaptionsListListener(this);
        mjwPlayerView.addOnCompleteListener(this);
        mjwPlayerView.addOnControlBarVisibilityListener(this);
        mjwPlayerView.addOnControlsListener(this);
        mjwPlayerView.addOnDisplayClickListener(this);
        mjwPlayerView.addOnErrorListener(this);
        mjwPlayerView.addOnFirstFrameListener(this);
        mjwPlayerView.addOnFullscreenListener(this);
        mjwPlayerView.addOnIdleListener(this);
        mjwPlayerView.addOnLevelsChangedListener(this);
        mjwPlayerView.addOnLevelsListener(this);
        mjwPlayerView.addOnMetaListener(this);
        mjwPlayerView.addOnMuteListener(this);
        mjwPlayerView.addOnPauseListener(this);
        mjwPlayerView.addOnPlaybackRateChangedListener(this);
        mjwPlayerView.addOnPlaylistCompleteListener(this);
        mjwPlayerView.addOnPlayListener(this);
        mjwPlayerView.addOnPlaylistItemListener(this);
        mjwPlayerView.addOnPlaylistListener(this);
        mjwPlayerView.addOnReadyListener(this);
        mjwPlayerView.addOnSeekedListener(this);
        mjwPlayerView.addOnSeekListener(this);
        mjwPlayerView.addOnSetupErrorListener(this);
        mjwPlayerView.addOnTimeListener(this);
        mjwPlayerView.addOnVisualQualityListener(this);
    }

    private void updateOutput(String output) {
        outputStringBuilder.append("").append(Time.currentTime()).append(" ").append(output).append("\r\n");
        mOutput.setText(outputStringBuilder.toString());
        mScroll.scrollTo(0,mOutput.getBottom());
    }

    private void print(String log){
        Log.i(MEASURE_TAG,"" + Time.currentTime() + log + "(Event)");
    }


    @Override
    public void onAdBreakEnd(AdBreakEndEvent adBreakEndEvent) {
        updateOutput(" onAdBreakEnd-pos: " + adBreakEndEvent.getAdPosition());
        print(" onAdBreakEnd-pos: " + adBreakEndEvent.getAdPosition());

    }

    @Override
    public void onAdBreakStart(AdBreakStartEvent adBreakStartEvent) {
        updateOutput(" onAdBreakStart-pos: " + adBreakStartEvent.getAdPosition());
        print(" onAdBreakStart-pos: " + adBreakStartEvent);

    }

    @Override
    public void onAdClick(AdClickEvent adClickEvent) {
        updateOutput(" onAdClick Client: " + adClickEvent.getClient() + "\r\n"
                                + " Tag: " + adClickEvent.getTag());
        print(" onAdClick Client: " + adClickEvent.getClient() + "\r\n"
                                + " Tag: " + adClickEvent.getTag());

    }

    @Override
    public void onAdCompanions(AdCompanionsEvent adCompanionsEvent) {
        updateOutput(" onAdCompanions-tag: " + adCompanionsEvent.getTag());
        print(" onAdCompanions-tag: " + adCompanionsEvent);

    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        updateOutput(" onAdComplete Client: " + adCompleteEvent.getClient() + "\r\n"
                                    + " Tag: " + adCompleteEvent.getTag());
        print(" onAdComplete Client: " + adCompleteEvent.getClient() + "\r\n"
                                    + " Tag: " + adCompleteEvent.getTag());

    }

    @Override
    public void onAdError(AdErrorEvent adErrorEvent) {
        updateOutput(" onAdError Message: " + adErrorEvent.getMessage()+ "\r\n"
                                 + " Tag: " + adErrorEvent.getTag());
        print(" onAdError Message: " + adErrorEvent.getMessage()+ "\r\n"
                                 + " Tag: " + adErrorEvent.getTag());

    }

    @Override
    public void onAdImpression(AdImpressionEvent adImpressionEvent) {
        updateOutput(" onAdImpression:  " + " \r\n"
                    + " AdTitle: " + adImpressionEvent.getAdTitle() + "\r\n"
                    + " MediaFile: " + adImpressionEvent.getMediaFile() + " \r\n"
                    + " Vmap Info: " + adImpressionEvent.getVmapInfo());
        print(" onAdImpression:  " + " \r\n"
            + " AdTitle: " + adImpressionEvent.getAdTitle() + "\r\n"
            + " MediaFile: " + adImpressionEvent.getMediaFile() + " \r\n"
            + " Vmap Info: " + adImpressionEvent.getVmapInfo());

    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        updateOutput(" AdPauseEvent:  " + adPauseEvent.getTag());
        print(" AdPauseEvent:  " + adPauseEvent.getTag());

    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        updateOutput(" onAdPlay:  " + adPlayEvent.getTag());
        print(" onAdPlay:  " + adPlayEvent.getTag());

    }

    @Override
    public void onAdRequest(AdRequestEvent adRequestEvent) {
        updateOutput(" onAdRequest:  " + " \r\n"
                    + " Pos: " + adRequestEvent.getAdPosition() + "\r\n"
                    + " Tag: " + adRequestEvent.getTag() + " \r\n"
                    + " Offset: " + adRequestEvent.getOffset());
        print(" onAdRequest:  " + " \r\n"
                + " Pos: " + adRequestEvent.getAdPosition() + "\r\n"
                + " Tag: " + adRequestEvent.getTag() + " \r\n"
                + " Offset: " + adRequestEvent.getOffset());

    }

    @Override
    public void onAdSchedule(AdScheduleEvent adScheduleEvent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            adScheduleEvent.getVmapAdBreaks().forEach(e -> {
                updateOutput(e.toString());
                print(e.toString());
            });
        }
        updateOutput(" onAdSchedule Tag: " + adScheduleEvent.getTag() + "\r\n"
                + " onAdSchedule size: "+ adScheduleEvent.getVmapAdBreaks().size());
        print(" onAdSchedule Tag: " + adScheduleEvent.getTag() + "\r\n"
                + " onAdSchedule size: "+ adScheduleEvent.getVmapAdBreaks().size());
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        updateOutput(" onAdSkipped Tag: " + adSkippedEvent.getTag());
        print(" onAdSkipped Tag: " + adSkippedEvent.getTag());

    }

    @Override
    public void onAdStarted(AdStartedEvent adStartedEvent) {
        updateOutput(" onAdStarted Tag: " + adStartedEvent.getTag());
        print(" onAdStarted Tag: " + adStartedEvent.getTag());

    }

    @Override
    public void onAdTime(AdTimeEvent adTimeEvent) {
//        updateOutput(" onAdTime:  " + " \r\n"
//                    + " client: " + adTimeEvent.getClient() + "\r\n"
//                    + " Tag: " + adTimeEvent.getTag() + " \r\n"
//                    + " Pos: " + adTimeEvent.getPosition() + " \r\n"
//                    + " Dur: " + adTimeEvent.getDuration());
//        print(" onAdTime:  " + " \r\n"
//                + " client: " + adTimeEvent.getClient() + "\r\n"
//                + " Tag: " + adTimeEvent.getTag() + " \r\n"
//                + " Pos: " + adTimeEvent.getPosition() + " \r\n"
//                + " Dur: " + adTimeEvent.getDuration());

    }

    @Override
    public void onBeforeComplete(BeforeCompleteEvent beforeCompleteEvent) {
        updateOutput(" onBeforeComplete: " + beforeCompleteEvent);
        print(" onBeforeComplete: " + beforeCompleteEvent);

    }

    @Override
    public void onBeforePlay(BeforePlayEvent beforePlayEvent) {
        updateOutput(" onBeforePlay: " + beforePlayEvent);
        print(" onBeforePlay: " + beforePlayEvent);

    }

    @Override
    public void onAudioTrackChanged(AudioTrackChangedEvent audioTrackChangedEvent) {
        updateOutput(" onAudioTrackChanged: " + audioTrackChangedEvent.getCurrentTrack());
        print(" onAudioTrackChanged: " + audioTrackChangedEvent.getCurrentTrack());

    }

    @Override
    public void onAudioTracks(AudioTracksEvent audioTracksEvent) {
        updateOutput(" onAudioTracks size: " + audioTracksEvent.getLevels().size());
        print(" onAudioTracks size: " + audioTracksEvent.getLevels().size());

    }

    @Override
    public void onBufferChange(BufferChangeEvent bufferChangeEvent) {
        updateOutput(" onBufferChange; percent=" + bufferChangeEvent.getBufferPercent() +
                    " position=" + bufferChangeEvent.getPosition() +
                    " duration=" + bufferChangeEvent.getDuration()
        );
        print(" onBufferChange; percent=" + bufferChangeEvent.getBufferPercent() +
                " position=" + bufferChangeEvent.getPosition() +
                " duration=" + bufferChangeEvent.getDuration()
        );

    }

    @Override
    public void onBuffer(BufferEvent bufferEvent) {
        updateOutput(" onBuffer: " + bufferEvent);
        print(" onBuffer: " + bufferEvent);

    }

    @Override
    public void onCaptionsChanged(CaptionsChangedEvent captionsChangedEvent) {
        updateOutput(" onCaptionsChanged: " + captionsChangedEvent.getCurrentTrack());
        print(" onCaptionsChanged: " + captionsChangedEvent.getCurrentTrack());

    }

    @Override
    public void onCaptionsList(CaptionsListEvent captionsListEvent) {
        updateOutput(" onCaptionsList size: " + captionsListEvent.getTracks().size());
        print(" onCaptionsList size: " + captionsListEvent.getTracks().size());

    }

    @Override
    public void onComplete(CompleteEvent completeEvent) {
        updateOutput(" onComplete: " + completeEvent);
        print(" onComplete: " + completeEvent);

    }

    @Override
    public void onControlBarVisibilityChanged(ControlBarVisibilityEvent controlBarVisibilityEvent) {
        updateOutput(" onControlBarVisibilityChanged:  " + controlBarVisibilityEvent.isVisible());
        print(" onControlBarVisibilityChanged:  " + controlBarVisibilityEvent.isVisible());

    }

    @Override
    public void onControls(ControlsEvent controlsEvent) {
        updateOutput(" onControls: " + controlsEvent.getControls());
        print(" onControls: " + controlsEvent.getControls());

    }

    @Override
    public void onDisplayClick(DisplayClickEvent displayClickEvent) {
        updateOutput(" onDisplayClick: " + displayClickEvent);
        print(" onDisplayClick: " + displayClickEvent);

    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        updateOutput(" onError: " + errorEvent.getMessage());
        print(" onError: " + errorEvent.getMessage());
        print(" onError: " + errorEvent.getException());

    }

    @Override
    public void onFirstFrame(FirstFrameEvent firstFrameEvent) {
        updateOutput(" onFirstFrame: " + firstFrameEvent.getLoadTime());
        print(" onFirstFrame: " + firstFrameEvent.getLoadTime());

    }

    @Override
    public void onFullscreen(FullscreenEvent fullscreenEvent) {
        updateOutput(" onFullscreen: " + fullscreenEvent.getFullscreen());
        print(" onFullscreen: " + fullscreenEvent.getFullscreen());

    }

    @Override
    public void onIdle(IdleEvent idleEvent) {
        updateOutput(" onIdle: " + idleEvent);
        print(" onIdle: " + idleEvent);

    }

    @Override
    public void onLevelsChanged(LevelsChangedEvent levelsChangedEvent) {
        updateOutput(" onLevelsChanged: " + levelsChangedEvent.getCurrentQuality());
        print(" onLevelsChanged: " + levelsChangedEvent.getCurrentQuality());

    }

    @Override
    public void onLevels(LevelsEvent levelsEvent) {
        updateOutput(" onLevels size: " + levelsEvent.getLevels().size());
        print(" onLevel size: " + levelsEvent.getLevels().size());

    }

    @Override
    public void onMeta(MetaEvent metaEvent) {
        updateOutput(" onMeta-videoID: " + metaEvent.getMetadata().getVideoId());
        print(" onMeta-videoID: " + metaEvent.getMetadata().getVideoId());

    }

    @Override
    public void onMute(MuteEvent muteEvent) {
        updateOutput(" onMute: " + muteEvent.getMute());
        print(" onMute: " + muteEvent.getMute());

    }

    @Override
    public void onPause(PauseEvent pauseEvent) {
        updateOutput(" onPause: " + pauseEvent);
        print(" onPause: " + pauseEvent);

    }

    @Override
    public void onPlay(PlayEvent playEvent) {
        updateOutput(" onPlay: " + playEvent);
        print(" onPlay: " + playEvent);

    }

    @Override
    public void onPlaybackRateChanged(float v) {
        updateOutput(" onPlaybackRateChanged: " + v);
        print(" onPlaybackRateChanged: " + v);

    }

    @Override
    public void onPlaylistComplete(PlaylistCompleteEvent playlistCompleteEvent) {
        updateOutput(" onPlaylistComplete: " + playlistCompleteEvent);
        print(" onPlaylistComplete: " + playlistCompleteEvent);

    }

    @Override
    public void onPlaylistItem(PlaylistItemEvent playlistItemEvent) {
        updateOutput(" onPlaylistItem:" + "\r\n "
                + " GetPlaylistItem: " + playlistItemEvent.getPlaylistItem() + "\r\n"
                + " Index: " + playlistItemEvent.getIndex());
        print(" onPlaylistItem:" + "\r\n "
                + " GetPlaylistItem: " + playlistItemEvent.getPlaylistItem() + "\r\n"
                + " Index: " + playlistItemEvent.getIndex());
    }

    @Override
    public void onPlaylist(PlaylistEvent playlistEvent) {
        updateOutput(" onPlaylist size: " + playlistEvent.getPlaylist().size());
        print(" onPlaylist size: " + playlistEvent.getPlaylist().size());

    }

    @Override
    public void onReady(ReadyEvent readyEvent) {
        updateOutput(" onReady-setuptime: " + readyEvent.getSetupTime());
        print(" onReady-setuptime: " + readyEvent.getSetupTime());

    }

    @Override
    public void onSeek(SeekEvent seekEvent) {
        updateOutput(" onSeek - Position: " + seekEvent.getPosition() + "\r\n"
                     + "onSeek - Offset: " + seekEvent.getOffset());
        print(" onSeek - Position: " + seekEvent.getPosition() + "\r\n"
            + "onSeek - Offset: " + seekEvent.getOffset());

    }

    @Override
    public void onSeeked(SeekedEvent seekedEvent) {
        updateOutput(" onSeeked: " + seekedEvent);
        print(" onSeeked: " + seekedEvent);

    }

    @Override
    public void onSetupError(SetupErrorEvent setupErrorEvent) {
        updateOutput(" onSetupError: " + setupErrorEvent.getMessage());
        print(" onSetupError: " + setupErrorEvent.getMessage());

    }

    @Override
    public void onTime(TimeEvent timeEvent) {
//        updateOutput(" onTime - duration: " + timeEvent.getDuration() +"\r\n"
//                    + "onTime - position: " + timeEvent.getPosition());
//        print(" onTime - duration: " + timeEvent.getDuration() +"\r\n"
//                    + "onTime - position: " + timeEvent.getPosition());

    }

    @Override
    public void onVisualQuality(VisualQualityEvent visualQualityEvent) {
        updateOutput(" onVisualQuality:  " + visualQualityEvent.getQualityLevel() );
        print(" onVisualQuality:  " + visualQualityEvent.getQualityLevel() );

    }
}
