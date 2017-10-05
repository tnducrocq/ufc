package fr.tnducrocq.ufc.presentation.ui.event.details;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.presentation.R;
import im.ene.toro.ToroPlayer;
import im.ene.toro.ToroUtil;
import im.ene.toro.exoplayer.SimpleExoPlayerViewHelper;
import im.ene.toro.media.PlaybackInfo;
import im.ene.toro.widget.Container;

/**
 * Created by tony on 12/09/2017.
 */

public class EventMediaViewHolder extends RecyclerView.ViewHolder implements ToroPlayer {

    SimpleExoPlayerViewHelper helper;
    Uri mediaUri;

    @BindView(R.id.event_player)
    SimpleExoPlayerView playerView;

    EventMediaViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final EventMedia media) {
        playerView.setUseController(true);
        mediaUri = Uri.parse(media.getMobileVideoUrl());
    }

    @NonNull
    @Override
    public View getPlayerView() {
        return playerView;
    }

    @NonNull
    @Override
    public PlaybackInfo getCurrentPlaybackInfo() {
        return helper != null ? helper.getLatestPlaybackInfo() : new PlaybackInfo();
    }

    @Override
    public void initialize(@NonNull Container container, @Nullable PlaybackInfo playbackInfo) {
        if (helper == null) {
            helper = new SimpleExoPlayerViewHelper(container, this, mediaUri);
        }
        helper.initialize(playbackInfo);
    }

    @Override
    public void release() {
        if (helper != null) {
            helper.release();
            helper = null;
        }
    }

    @Override
    public void play() {
        if (helper != null) helper.play();
    }

    @Override
    public void pause() {
        if (helper != null) helper.pause();
    }

    @Override
    public boolean isPlaying() {
        return helper != null && helper.isPlaying();
    }

    @Override
    public boolean wantsToPlay() {
        return ToroUtil.visibleAreaOffset(this, itemView.getParent()) >= 0.85;
    }

    @Override
    public void onContainerScrollStateChange(Container container, int newState) {
        // Do nothing
    }

    @Override
    public int getPlayerOrder() {
        return getAdapterPosition();
    }

    @Override
    public String toString() {
        return "ExoPlayer{" + hashCode() + " " + getAdapterPosition() + "}";
    }

}
