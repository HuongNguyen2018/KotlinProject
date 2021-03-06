package com.hhmusic

import android.app.Application
import android.content.Context
import com.hhmusic.utilities.PlayerManager

class HHMusicApplication: Application(){

    private var playerManager: PlayerManager? = null

    init {
        instance = this
    }

    // create static
    companion object {

        private var instance: HHMusicApplication ? = null

        fun  applicationContext(): Context{
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = HHMusicApplication.applicationContext()
    }

    fun setPlayerManager(manager: PlayerManager?) {
        playerManager = manager
    }

    fun getPlayerManager() : PlayerManager? {
        return playerManager
    }
}
