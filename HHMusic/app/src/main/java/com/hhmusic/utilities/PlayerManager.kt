package com.hhmusic.utilities

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.FileDataSourceFactory
import com.hhmusic.data.entities.Song

class PlayerManager private constructor(
    private val context: Context,
    private var playerEventListener: Player.EventListener?
){
    private var player: SimpleExoPlayer? = null

    private var songList: ArrayList<Song>? = null
    private var mediaSource: MediaSource? = null

    // this is for miniMusic bottom toolbar, and will be obsered by MainActivity
    private var currentPlayedSong = MutableLiveData<Song>()
    private var currentPlayedStatus = MutableLiveData<Boolean>()

    private var startAutoPlay: Boolean = true
    private var startWindow: Int = C.INDEX_UNSET
    private var startPosition: Long = C.TIME_UNSET
    private var currentIndex : Int =-1

    var isPlaying: Boolean = false
    get() = field
    set(value) {
        field = value
        currentPlayedStatus.value = value
    }

    init {
        initializePlayer()
    }

    companion object {
        @Volatile private var INSTANCE: PlayerManager ? = null
        fun getInstance(ctx: Context, listener: Player.EventListener?): PlayerManager{
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = PlayerManager(ctx, listener)
                }
            }
            return INSTANCE!!
        }
    }

    fun initializePlayer() {
        player?:let{
            player = ExoPlayerFactory.newSimpleInstance(
                context, DefaultRenderersFactory(context)
                , DefaultTrackSelector(),
                DefaultLoadControl()
            )

            player?.apply {
                // AudioAttributes here from exoplayer package !!!
                val attr = AudioAttributes.Builder().setUsage(C.USAGE_MEDIA)
                    .setContentType(C.CONTENT_TYPE_MUSIC)
                    .build()
                // In 2.9.X you don't need to manually handle audio focus :D
                setAudioAttributes(attr, true)

                if (playerEventListener != null)
                    addListener(playerEventListener)

                playWhenReady = startAutoPlay
            }
        }
    }

    fun getPlayer() = player

    fun getSongList(): ArrayList<Song>? {
        return songList
    }
    fun setSongList(list: ArrayList<Song>, idx: Int) {
        if (list.size > 0) {
            songList = list
            currentIndex = idx
            var uris: ArrayList<Uri> = ArrayList()
            for (s: Song in songList!!) {
                var uri = Uri.parse(s?.uriStr)
                uris.add(uri)
            }

            // build media sources for player
            val mediaSources = arrayOfNulls<MediaSource>(uris.size)
            for (i in uris.indices) {
                mediaSources[i] = buildMediaSource(uris[i])
            }
            mediaSource = if (mediaSources.size == 1) mediaSources[0] else ConcatenatingMediaSource(*mediaSources)
           // mediaSource = if (mediaSources.size == 1) mediaSources[0] else DynamicConcatenatingMediaSource(*mediaSources)
        }
    }

    fun setCurrentPlayedSong(song: Song) {
        currentPlayedSong.value = song
    }
    fun getCurrentPlayedSong() : LiveData<Song> {
        return currentPlayedSong
    }

    fun getCurrentPlayedStatus() : LiveData<Boolean> {
        return currentPlayedStatus
    }

    fun setPlayerEventListener(_listener: Player.EventListener) {
        playerEventListener = _listener
        player?.addListener(playerEventListener)
    }

    fun play() {
        if (!isPlaying) {
            // seek player to previous position
            val haveStartPosition = startWindow != C.INDEX_UNSET
            if (haveStartPosition) {
                player?.seekTo(startWindow, startPosition)
            }

            player?.prepare(mediaSource, !haveStartPosition, false)
            if (currentIndex >= 0)
                player?.seekTo(currentIndex, startPosition)
            else
                currentIndex = -1
            isPlaying = true
        }
    }

    fun retry() {
        player?.retry()
        isPlaying = true
    }

    fun stop() {
        if (isPlaying) {
            updateStartPosition()
            player?.stop(false)

            isPlaying = false
        }
    }

    fun pause() {
        player?.setPlayWhenReady(false);
        player?.getPlaybackState();

        isPlaying = false
    }

    fun resume() {
        player?.setPlayWhenReady(true);
        player?.getPlaybackState();

        isPlaying = true
    }

    fun setCurrentSongIndex(currentSong: Int) {
        if (currentSong <= songList!!.size && currentSong >=0 )
            currentIndex = currentSong;
    }

    fun updateStartPosition(_startAutoPlay: Boolean, _startWindow: Int, _startPosition: Long) {
        if (player != null) {
            startAutoPlay = _startAutoPlay
            startWindow = _startWindow
            startPosition = _startPosition
        }
    }

    fun updateStartPosition() {
        if (player != null) {
            startAutoPlay = player?.getPlayWhenReady() ?: true
            startWindow = player?.getCurrentWindowIndex() ?: C.INDEX_UNSET
            startPosition = Math.max(0, player?.getContentPosition() ?: C.TIME_UNSET)
        }
    }

    fun clearStartPosition() {
        startAutoPlay = true
        startWindow = C.INDEX_UNSET
        startPosition = C.TIME_UNSET
    }

    fun removeMediaSource() {
        mediaSource = null
    }
    fun releasePlayer() {
        if (player != null) {
            //updateStartPosition()
            clearStartPosition()

            player?.release()
            player = null
            //mediaSource = null
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = FileDataSourceFactory()
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }
}