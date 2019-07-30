package com.hhmusic.viewmodels

import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.lifecycle.*
import com.example.android.uamp.media.extensions.*
import com.hhmusic.common.MediaSessionConnection
import com.hhmusic.data.entities.Song
import com.hhmusic.R
import com.hhmusic.common.EMPTY_PLAYBACK_STATE
import com.hhmusic.common.NOTHING_PLAYING

class PlayerViewModel(private val app: Application,
                      mediaSessionConnection: MediaSessionConnection) : ViewModel() {
    data class NowPlayingMetadata(
        val id: String,
        val albumArtUri: Uri,
        val title: String?,
        val subtitle: String?,
        val duration: String
    ) {

        companion object {
            /**
             * Utility method to convert milliseconds to a display of minutes and seconds
             */
            fun timestampToMSS(context: Context, position: Long): String {
                val totalSeconds = Math.floor(position / 1E3).toInt()
                val minutes = totalSeconds / 60
                val remainingSeconds = totalSeconds - (minutes * 60)
                return if (position < 0) context.getString(R.string.duration_unknown)
                else context.getString(R.string.duration_format).format(minutes, remainingSeconds)
            }
        }
    }

    companion object {
        /**
         * Utility method to convert milliseconds to a display of minutes and seconds
         */
        fun timestampToMSS(context: Context, position: Long): String {
            val totalSeconds = Math.floor(position / 1E3).toInt()
            val minutes = totalSeconds / 60
            val remainingSeconds = totalSeconds - (minutes * 60)
            return if (position < 0) context.getString(R.string.duration_unknown)
            else context.getString(R.string.duration_format).format(minutes, remainingSeconds)
        }
    }

    private var playbackState : PlaybackStateCompat = EMPTY_PLAYBACK_STATE

    val mediaMetadata = MutableLiveData<Song>()
    val mediaPosition = MutableLiveData<Long>().apply {
        postValue(0L)
    }

    private var updatePosition = true
    private val handler = Handler(Looper.getMainLooper())

    private val mediaMetadataObserver = Observer<MediaMetadataCompat>{
       // upda
    }
    val mediaButtonRes = MutableLiveData<Int>().apply {
        postValue(R.drawable.ic_album_black_24dp)
    }

    private fun updateState(
    playbackState: PlaybackStateCompat,
    song:
   // mediaMetadata: MediaMetadataCompat
) {
    if (mediaMetadata.duration != 0L) {
        val nowPlayingMetadata = NowPlayingMetadata(
            mediaMetadata.id,
            mediaMetadata.albumArtUri,
            mediaMetadata.title?.trim(),
            mediaMetadata.displaySubtitle?.trim(),
            NowPlayingMetadata.timestampToMSS(app, mediaMetadata.duration)
        )
        this.mediaMetadata.postValue(nowPlayingMetadata)
    }

    // Update the media button resource ID
    mediaButtonRes.postValue(
        when (playbackState.isPlaying) {
            true -> R.drawable.ic_pause_black_24dp
            else -> R.drawable.ic_play_arrow_black_24dp
        }
    )
}



}