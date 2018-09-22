package com.yaratech.yaratube.ui.player;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.yaratech.yaratube.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity {
    public static String PLAYER_ACTIVITY_KEY = "Player_Activity";
    SimpleExoPlayer player;

    @BindView(R.id.player_view)
    PlayerView playerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    private String PLAYER_TAG = "player_activity";
    private String TAG = "player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        hideStatusBar();
        progressBar.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        String videoUri = intent.getStringExtra(PLAYER_ACTIVITY_KEY);
        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());

//        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
//                Util.getUserAgent(this, "ExoPlayer"));

        HlsMediaSource mediaSource = new HlsMediaSource
                .Factory(new CacheDataSourceFactory(
                getApplicationContext(),
                100 * 1024 * 1024,
                5 * 1024 * 1024))
                .createMediaSource(Uri.parse(videoUri));
        player.prepare(mediaSource);
        player.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == ExoPlayer.STATE_BUFFERING) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });
        player.setPlayWhenReady(true);
        playerView.setPlayer(player);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Bundle bundle = new Bundle();
//        bundle.putLong(PLAYER_TAG, player.getCurrentPosition());
//        player.stop();
//        player.release();
//        player = null;
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("hmmmm", player.getCurrentPosition() + "");
        if (player != null) {
            outState.putLong(PLAYER_TAG, player.getCurrentPosition());
//            player.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState: "+savedInstanceState+" "+savedInstanceState.getLong(PLAYER_TAG) );
        if (savedInstanceState != null) {
            player.seekTo(savedInstanceState.getLong(PLAYER_TAG));
            savedInstanceState.getLong(PLAYER_TAG);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        player.setPlayWhenReady(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.setPlayWhenReady(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerView.setPlayer(null);
        player.release();
    }
}
