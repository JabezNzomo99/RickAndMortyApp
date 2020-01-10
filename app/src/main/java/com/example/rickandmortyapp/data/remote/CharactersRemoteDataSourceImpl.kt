package com.example.rickandmortyapp.data.remote

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.example.rickandmortyapp.data.model.*
import com.example.rickandmortyapp.graphql.GetAllCharactersQuery
import com.example.rickandmortyapp.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CharactersRemoteDataSourceImpl(private val apolloClient: ApolloClient,
                                     private val ioDispatcher: CoroutineDispatcher = Dispatchers.Main): CharactersRemoteDataSource {

    val characters = mutableListOf<Character>()

    override suspend fun fetchCharacters(page:Int) : Result<List<Character>> = withContext(ioDispatcher) {
        val getAllCharactersQuery:GetAllCharactersQuery = GetAllCharactersQuery.builder().page(page).build()
        return@withContext try {
            Log.d("PAGE", page.toString())
            val deferred = apolloClient.query(getAllCharactersQuery).toDeferred()
            val response = deferred.await()
            if (!response.hasErrors() && response.data() != null) {
                val data = response.data()
                val next = data?.characters()?.info()?.next()
                val pages = data?.characters()?.info()?.pages()
                data?.characters()?.results()?.forEach {result->
                    val episodes = mutableListOf<Episode>()
                    result.episode()?.forEach {episode->
                        episodes.add(
                        Episode(
                            air_date = episode.air_date(),
                            episode = episode.episode(),
                            id = episode.id(),
                            characterId = result.id(),
                            name = episode.name()
                        ))
                    }
                    val character = Character(
                        id= result.id()!!,
                        created = result.created(),
                        episode = episodes,
                        gender = result.gender(),
                        image = result.image(),
                        name = result.name(),
                        species = result.species(),
                        status = result.status(),
                        type = result.type()
                    )
                    characters.add(character)
                }
                if(next!=null && pages!=null) {
                    if (next <= pages) {
                        fetchCharacters(next)
                    }
                }
            }

            Result.Success(characters)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}