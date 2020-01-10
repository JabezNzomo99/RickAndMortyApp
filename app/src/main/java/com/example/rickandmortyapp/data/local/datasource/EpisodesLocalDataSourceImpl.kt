package com.example.rickandmortyapp.data.local.datasource

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.data.local.dao.EpisodeDao
import com.example.rickandmortyapp.data.model.Episode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EpisodesLocalDataSourceImpl(private val episodeDao: EpisodeDao,
                                  private val ioDispatcher:CoroutineDispatcher = Dispatchers.IO) : EpisodesLocalDataSource {

    override suspend fun addEpisodes(episodes: List<Episode>)  = withContext(ioDispatcher){
        episodeDao.addEpisodes(episodes = episodes)
    }

    override suspend fun clear()= withContext(ioDispatcher){
        episodeDao.clear()
    }

    override suspend fun getEpisodesPerCharacter(characterId: String): LiveData<List<Episode>> = withContext(ioDispatcher){
        return@withContext episodeDao.getEpisodesPerCharacter(characterId = characterId)
    }
}