package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchCharacters()
    }

    private fun fetchCharacters(){
        mainActivityViewModel.fetchCharacters()
    }
}
