package com.example.rickandmortyapp.ui.home.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.rickandmortyapp.data.repository.CharactersRepository

class EpisodeFragmentViewModel(private val charactersRepository: CharactersRepository): ViewModel() {

    fun getEpisodes(characterId:String) = liveData {
        emitSource(charactersRepository.getEpisodesPerCharacter(characterId))
    }
}
