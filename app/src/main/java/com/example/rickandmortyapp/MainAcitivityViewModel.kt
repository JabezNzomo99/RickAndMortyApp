package com.example.rickandmortyapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.repository.CharactersRepository
import com.example.rickandmortyapp.util.Event
import com.example.rickandmortyapp.util.Result
import kotlinx.coroutines.launch


class MainAcitivityViewModel(private val charactersRepository:CharactersRepository): ViewModel(){

    val dataFetchState:MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isFetching:MutableLiveData<Boolean> = MutableLiveData()


    fun fetchCharacters() = viewModelScope.launch {
        isFetching.value = (true)
        when(val result=charactersRepository.fetchCharacters()){
            is Result.Success->{
                dataFetchState.value = Event(result.data)
            }
            is Result.Error ->{
                dataFetchState.value = Event(false)
            }
        }
        isFetching.value = (false)
    }

}