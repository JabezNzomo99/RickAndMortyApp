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

    fun fetchCharacters() = viewModelScope.launch {
        when(val result=charactersRepository.fetchCharacters()){
            is Result.Success->{
                dataFetchState.value = result.data
            }
            is Result.Error ->{
                dataFetchState.value = false
            }
        }
    }
}