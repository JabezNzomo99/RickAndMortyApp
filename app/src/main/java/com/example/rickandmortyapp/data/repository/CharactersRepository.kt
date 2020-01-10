package com.example.rickandmortyapp.data.repository

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.model.Episode

interface CharactersRepository {

    suspend fun fetchCharacters()
    suspend fun getCharacters():LiveData<List<Character>>
    suspend fun searchCharacters(searchString: String):LiveData<List<Character>>
    suspend fun getEpisodesPerCharacter(characterId:String):LiveData<List<Episode>>
}