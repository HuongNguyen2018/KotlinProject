package com.hhmusic.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hhmusic.HHMusicApplication
import com.hhmusic.data.entities.Song
import com.hhmusic.databinding.SongListItemBinding
import com.hhmusic.ui.activity.MainActivity

class SongListAdapter(private val myActivity: MainActivity): ListAdapter<Song, SongListAdapter.SongListViewHolder>(SongDiffCallback()) {

    lateinit var songList: List<Song>;

    fun setSongList(list : ArrayList<Song>){
        songList = ArrayList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder {

        return  SongListViewHolder(SongListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false ))

    }

    override fun onBindViewHolder(holder: SongListAdapter.SongListViewHolder, position: Int) {

        val song: Song = getItem(position)
        holder.apply {
            bind(createOnClickListener(song), song)
            itemView.tag = song
        }
    }


    private fun createOnClickListener(song: Song): View.OnClickListener {
        return View.OnClickListener {
            //val direction = PlantListFragmentDirections.ActionPlantListFragmentToPlantDetailFragment(plantId)
            //it.findNavController().navigate(direction)

            val context =  MainActivity.intent(it.context, ArrayList(songList))
            myActivity.openPlayerScreen()

            Toast.makeText(HHMusicApplication.applicationContext(), "Play song", Toast.LENGTH_SHORT).show()
        }
    }

     class SongListViewHolder(private val binding: SongListItemBinding): RecyclerView.ViewHolder(binding.root) {

         fun bind(listener: View.OnClickListener, item: Song) {
             binding.apply {
                 clickListener = listener
                 songItem = item
                 executePendingBindings()
             }
         }


     }


    private class SongDiffCallback : DiffUtil.ItemCallback<Song>() {

        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }
}

