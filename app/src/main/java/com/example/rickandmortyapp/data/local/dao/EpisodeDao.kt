package com.example.rickandmortyapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapp.data.model.Episode
import retrofit2.http.DELETE

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEpisodes(episodes:List<Episode>)

    @Query("DELETE FROM episodes")
    suspend fun clear()

    @Query("SELECT * FROM episodes WHERE character_id=:characterId")
    fun getEpisodesPerCharacter(characterId:String):LiveData<List<Episode>>
}