package com.example.rickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortyapp.data.local.dao.CharacterDao
import com.example.rickandmortyapp.data.local.dao.EpisodeDao
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.model.Episode


@Database(
    entities = [Character::class,Episode::class],
    version = 1,
    exportSchema = false
) abstract class Database:RoomDatabase(){
    abstract fun getCharacterDao():CharacterDao
    abstract fun getEpisodeDao():EpisodeDao
}