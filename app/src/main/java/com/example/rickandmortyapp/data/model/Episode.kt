package com.example.rickandmortyapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "generated_id")
    val generated_id:Int = 0,
    @ColumnInfo(name = "air_date")
    val air_date: String?=null,
    @ColumnInfo(name = "episode")
    val episode: String?=null,
    @ColumnInfo(name = "episode_id")
    val id: String?,
    @ColumnInfo(name = "character_id")
    val characterId:String?=null,
    @ColumnInfo(name = "episode_name")
    val name: String?=null
)