package com.example.rickandmortyapp.data.repository

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.data.local.datasource.CharactersLocalDataSource
import com.example.rickandmortyapp.data.local.datasource.EpisodesLocalDataSource
import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.model.Episode
import com.example.rickandmortyapp.util.Result
import com.example.rickandmortyapp.data.remote.CharactersRemoteDataSource

class CharactersRepositoryImpl(private val charactersLocalDataSource: CharactersLocalDataSource,
                               private val episodesLocalDataSource: EpisodesLocalDataSource,
                               private val charactersRemoteDataSource: CharactersRemoteDataSource): CharactersRepository{

    companion object{
        const val DEFAULT_PAGE = 1
    }

    override suspend fun fetchCharacters() : Result<Boolean>{
        return when(val result=charactersRemoteDataSource.fetchCharacters(DEFAULT_PAGE)){
            is Result.Success->{
                val characters = result.data
                charactersLocalDataSource.addCharacters(characters)
                characters.forEach {character->
                    if(!character.episode.isNullOrEmpty()){
                        episodesLocalDataSource.addEpisodes(character.episode)
                    }
                }
                Result.Success(true)
            }
            is Result.Error->{
                Result.Error(result.exception)

            }
            else->Result.Success(false)

        }
    }

    override suspend fun getCharacters(): LiveData<List<Character>> = charactersLocalDataSource.getAllCharacters()
    override suspend fun searchCharacters(searchString: String): LiveData<List<Character>> = charactersLocalDataSource.searchCharacters(searchString)
    override suspend fun getEpisodesPerCharacter(characterId: String): LiveData<List<Episode>> = episodesLocalDataSource.getEpisodesPerCharacter(characterId)

}
