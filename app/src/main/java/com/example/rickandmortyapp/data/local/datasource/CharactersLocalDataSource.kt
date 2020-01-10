package com.example.rickandmortyapp.data.local.datasource

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.data.model.Character

interface CharactersLocalDataSource {
    suspend fun addCharacters(characters:List<Character>)
    suspend fun addCharacter(character:Character)
    suspend fun getAllCharacters():LiveData<List<Character>>
    suspend fun getCharacter(characterId:Int):LiveData<Character>
    suspend fun clear()
    suspend fun searchCharacters(searchString:String):LiveData<List<Character>>
}