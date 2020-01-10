package com.example.rickandmortyapp.util

import com.example.rickandmortyapp.data.model.Character

interface ItemClickListener {
    fun onClick(character:Character)
}