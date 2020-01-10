package com.example.rickandmortyapp.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = false) @NonNull
    @ColumnInfo(name = "character_id")
    var id: String,
    @ColumnInfo(name = "created")
    var created: String?=null,
    @Ignore
    val episode: List<Episode>?=null,
    @ColumnInfo(name = "gender")
    var gender: String?=null,
    @ColumnInfo(name = "image")
    var image: String?=null,
    @ColumnInfo(name = "name")
    var name: String?=null,
    @ColumnInfo(name = "species")
    var species: String?=null,
    @ColumnInfo(name = "status")
    var status: String?=null,
    @ColumnInfo(name = "type")
    var type: String?=null
){
    constructor():this("","", emptyList(),"","","","","","")

}