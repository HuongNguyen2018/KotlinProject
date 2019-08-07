package com.hhmusic.ui.activity

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity;
import com.hhmusic.data.entities.Song

import com.hhmusic.ui.fragment.NowPlayingFragment


class PlayerActivity : AppCompatActivity() {

    companion object {
        val ACTION_VIEW = "com.hhmusic.android.action.VIEW"
        //val ACTION_VIEW_LIST = "com.hhmusic.android.action.VIEW_LIST"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        // set background color
        setTheme(com.hhmusic.R.style.PlayerTheme_Spherical)

        super.onCreate(savedInstanceState)
        setContentView(com.hhmusic.R.layout.activity_player)

        // receiving our song from Main Activity
        val action = intent.action
        if (ACTION_VIEW == action) {
            val song: Song? = intent.getParcelableExtra<Parcelable>(MainActivity.KEY_SONGS) as Song

            var fragment1 = NowPlayingFragment(this)
            val bundle1 = Bundle()
            bundle1.putParcelable(MainActivity.KEY_SONGS, song)
            fragment1.setArguments(bundle1)

            supportFragmentManager.beginTransaction()
                .replace(com.hhmusic.R.id.fragment_container, fragment1, NowPlayingFragment.TAG).commit()
        }
    }
}
