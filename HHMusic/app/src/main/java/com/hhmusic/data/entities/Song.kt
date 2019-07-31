package com.hhmusic.data.entities

import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Nullable
import com.hhmusic.utilities.HHMusicConstants
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@SuppressLint("ParcelCreator")
@Entity(tableName = "songs")
data class Song(  @PrimaryKey @ColumnInfo(name = "id") var songId: Long = 0,
                    @ColumnInfo(name = "title") var title: String = "",
                    @ColumnInfo(name = "artistName") var artistName: String = "",
                    @ColumnInfo(name = "albumName") var albumName: String = "",
                    @ColumnInfo(name = "duration") var duration: Long = 0,
                    @ColumnInfo(name = "imagePathStr") var imagePathStr: String = ""
) : Parcelable{


    constructor(parcel: Parcel) : this(parcel.readLong()) {
        songId = parcel.readLong()
        title = parcel.readString()
        artistName = parcel.readString()
        albumName = parcel.readString()
        imagePathStr = parcel.readString()
        duration = parcel.readLong()
    }

//    public constructor(id: Long, title: String, artistName: String, albumName: String, duration: Long, imagePath: String): this(id) {
//        this.songId = id
//        this.title = title
//        this.artistName = artistName
//        this.albumName = albumName
//        this.duration = duration
//        this.imagePathStr = imagePath
//
//
//    }

    companion object {


        /**
         * Create a new [Song] from the specified [ContentValues].
         *
         * @param values A [ContentValues] that at least contain [.COLUMN_NAME].
         * @return A newly created [Song] instance.
         */
        fun fromContentValues(values: ContentValues): Song {
            //values?.let {
                val song = Song()

                if (values.containsKey("id")) {
                    song.songId = values.getAsLong("id")!!
                }
                if (values.containsKey("title")) {
                    song.title = values.getAsString("title")
                }
                if (values.containsKey("artistName")) {
                    song.artistName = values.getAsString("artistName")
                }
                if (values.containsKey("albumName")) {
                    song.albumName = values.getAsString("albumName")
                }
                if (values.containsKey("duration")) {
                    song.duration = values.getAsLong("duration")
                }
                if (values.containsKey("imagePathStr")) {
                    song.imagePathStr = values.getAsString("imagePathStr")
                }

                return song
            //}
            //return null!!
        }
    }

    public fun getImageUrl(): String?{
        return imagePathStr
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


    override fun toString() = title

}
