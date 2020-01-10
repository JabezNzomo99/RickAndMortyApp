package com.example.rickandmortyapp.data.repository

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.model.Episode
import com.example.rickandmortyapp.util.Result

interface CharactersRepository {

    suspend fun fetchCharacters():Result<Boolean>
    suspend fun getCharacters():LiveData<List<Character>>
    suspend fun searchCharacters(searchString: String):LiveData<List<Character>>
    suspend fun getEpisodesPerCharacter(characterId:String):LiveData<List<Episode>>
}