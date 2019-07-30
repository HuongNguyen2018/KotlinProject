package com.hhmusic.data.entities

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Nullable
import com.hhmusic.utilities.HHMusicConstants

data class Song(public var id: Long) : Parcelable{

    lateinit var title: String
    lateinit var artistName: String
    lateinit var albumName: String
    lateinit var imagePath: Uri
    var duration: Long = 0

    constructor(parcel: Parcel) : this(parcel.readLong()) {
        id = parcel.readLong()
        title = parcel.readString()
        artistName = parcel.readString()
        albumName = parcel.readString()
        imagePath = parcel.readParcelable(Uri::class.java.classLoader)
        duration = parcel.readLong()
    }

    public constructor(id: Long, title: String, artistName: String, albumName: String, duration: Long, imagePath: Uri): this(id) {
        this.id = id
        this.title = title
        this.artistName = artistName
        this.albumName = albumName
        this.duration = duration
        this.imagePath = imagePath

    }

    public fun getImageUrl(): String?{

        if (imagePath != null) {
            return imagePath.toString()
        } else
            return null
    }
    public fun getDurationFormat(): String?{

        return HHMusicConstants.setCorrectDuration(duration)
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(artistName)
        dest?.writeString(albumName)
        dest?.writeLong(duration)
        dest?.writeString(getImageUrl())

    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }


}

