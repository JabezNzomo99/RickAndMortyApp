package com.example.rickandmortyapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Episode
import kotlinx.android.synthetic.main.row_episode.view.*

class EpisodeAdapter(private val episodes:List<Episode>) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {
    class EpisodeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(episode:Episode){
            itemView.textViewEpisode.text = episode.episode
            itemView.textViewEpisodeAirDate.text = episode.air_date
            itemView.textViewEpisodeName.text = episode.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_episode,parent,false))
    }

    override fun getItemCount(): Int = episodes.size

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }
}