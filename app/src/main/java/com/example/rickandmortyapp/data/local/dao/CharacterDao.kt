package com.example.rickandmortyapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapp.data.model.Character
import retrofit2.http.GET

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters:List<Character>)

    @Query("DELETE FROM characters")
    suspend fun clear()

    @Query("SELECT * FROM characters")
    fun getAllCharacters():LiveData<List<Character>>

    @Query("SELECT * FROM characters WHERE character_id=:characterId ")
    fun getCharacterById(characterId:Int):LiveData<Character>

    @Query("SELECT * FROM characters WHERE name LIKE :searchString OR type LIKE :searchString OR status LIKE :searchString OR gender LIKE :searchString OR species LIKE :searchString")
    fun searchCharacters(searchString: String) : LiveData<List<Character>>
}