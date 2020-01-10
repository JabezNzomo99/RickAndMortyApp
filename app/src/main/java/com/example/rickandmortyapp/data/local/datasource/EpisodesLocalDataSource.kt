package com.example.rickandmortyapp.data.local.datasource

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.data.model.Episode

interface EpisodesLocalDataSource {
    suspend fun addEpisodes(episodes:List<Episode>)
    suspend fun clear()
    suspend fun getEpisodesPerCharacter(characterId:String):LiveData<List<Episode>>
}