package com.example.rickandmortyapp.data.model

import com.example.rickandmortyapp.graphql.GetAllCharactersQuery


data class Characters(
    val info: Info,
    val results: List<GetAllCharactersQuery.Result>
)