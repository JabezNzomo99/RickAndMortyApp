package com.example.rickandmortyapp.data.local.datasource

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.data.local.dao.CharacterDao
import com.example.rickandmortyapp.data.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersLocalDataSourceImpl(private val characterDao: CharacterDao,
                                    private val ioDispatcher:CoroutineDispatcher = Dispatchers.IO): CharactersLocalDataSource {
    override suspend fun addCharacters(characters: List<Character>) = withContext(ioDispatcher){
        characterDao.addCharacters(characters = characters)
    }

    override suspend fun addCharacter(character: Character) = withContext(ioDispatcher){
        characterDao.addCharacter(character = character)
    }

    override suspend fun getAllCharacters(): LiveData<List<Character>> = withContext(ioDispatcher){
        return@withContext characterDao.getAllCharacters()
    }


    override suspend fun getCharacter(characterId: Int): LiveData<Character> = withContext(ioDispatcher){
        return@withContext characterDao.getCharacterById(characterId = characterId)
    }

    override suspend fun clear() = withContext(ioDispatcher){
        characterDao.clear()
    }

    override suspend fun searchCharacters(searchString: String): LiveData<List<Character>> = withContext(ioDispatcher){
        return@withContext characterDao.searchCharacters(searchString)
    }
}