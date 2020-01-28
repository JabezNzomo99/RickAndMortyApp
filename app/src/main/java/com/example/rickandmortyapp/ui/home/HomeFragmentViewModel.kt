package com.example.rickandmortyapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.repository.CharactersRepository
import com.example.rickandmortyapp.util.Result
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val charactersRepository: CharactersRepository): ViewModel(){

    val dataFetchState = MutableLiveData<Boolean>()
    val isFetching = MutableLiveData<Boolean>()


    fun getAllCharacters() = liveData {
        isFetching.postValue(true)
        emitSource(charactersRepository.getCharacters())
        isFetching.postValue(false)
    }

    fun searchCharaters(searchString:String)= liveData {
        emitSource(charactersRepository.searchCharacters(searchString))
    }


}