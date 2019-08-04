package com.hhmusic.utilities

import android.content.Context
import com.hhmusic.data.HHMusicDatabase
import com.hhmusic.data.repository.MusicRepository
import com.hhmusic.viewmodels.*

object InjectorUtils {

    fun providePlayListViewModelFactory(context: Context): PlayListViewModelFactory {
        val repository = getMusicRepository(context)
        return PlayListViewModelFactory(repository)
    }

    fun provideSongViewModelFactory(context: Context): SongViewModelFactory {
        val repository = getMusicRepository(context)
        return SongViewModelFactory(repository)
    }
    fun provideArtistViewModelFactory(context: Context): ArtistViewModelFactory {
        val repository = getMusicRepository(context)
        return ArtistViewModelFactory(repository)
    }

    fun provideAlbumViewModelFactory(context: Context): AlbumViewModelFactory {
        val repository = getMusicRepository(context)
        return AlbumViewModelFactory(repository)
    }
    private fun getMusicRepository(context: Context): MusicRepository {
        return MusicRepository.getInstance(
            HHMusicDatabase.getInstance(context.applicationContext).playListDao(),
            HHMusicDatabase.getInstance(context.applicationContext).songsDao(),
            HHMusicDatabase.getInstance(context.applicationContext).artistsDao(),
            HHMusicDatabase.getInstance(context.applicationContext).albumDao())
    }
}