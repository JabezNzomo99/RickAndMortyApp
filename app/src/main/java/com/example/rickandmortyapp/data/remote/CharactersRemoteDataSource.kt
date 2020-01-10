package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.util.Result

interface CharactersRemoteDataSource {
    suspend fun fetchCharacters(page:Int): Result<List<Character>>
}