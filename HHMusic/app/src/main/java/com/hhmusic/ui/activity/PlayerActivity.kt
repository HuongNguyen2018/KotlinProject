package com.hhmusic.ui.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.hhmusic.R
import com.hhmusic.data.entities.Song

import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        setSupportActionBar(toolbar)

        val bundle: Bundle? = intent.extras

        bundle?.let {

            bundle.apply {

                val songList: ArrayList<Song>? = getParcelableArrayList<Song>(MainActivity.KEY_SONG)
                val songId = getLong(MainActivity.KEY_SONG_ID)
                val songPosition: Int = getInt(MainActivity.KEY_SONG_POSITION)
            }
        }

    }




}
