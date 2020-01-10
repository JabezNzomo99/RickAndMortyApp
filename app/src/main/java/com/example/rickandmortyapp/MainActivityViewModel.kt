package com.example.rickandmortyapp

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.repository.CharactersRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val charactersRepository: CharactersRepository) : ViewModel(){

    fun fetchCharacters()= viewModelScope.launch {
        charactersRepository.fetchCharacters()
    }
}