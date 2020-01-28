package com.example.rickandmortyapp.ui.home.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.rickandmortyapp.data.repository.CharactersRepository


class AboutFragmentViewModel(private val charactersRepository: CharactersRepository) : ViewModel(){

    fun getCharacter(characterId:String)= liveData{
        emitSource(charactersRepository.getCharacter(characterId))
    }
}