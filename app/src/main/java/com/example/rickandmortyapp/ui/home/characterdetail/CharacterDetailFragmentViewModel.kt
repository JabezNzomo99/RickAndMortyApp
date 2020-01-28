package com.example.rickandmortyapp.ui.home.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.repository.CharactersRepository

class CharacterDetailFragmentViewModel(private val charactersRepository: CharactersRepository): ViewModel() {


    fun getCharacter(characterId : String) = liveData {
        emitSource(charactersRepository.getCharacter(characterId))
    }
}