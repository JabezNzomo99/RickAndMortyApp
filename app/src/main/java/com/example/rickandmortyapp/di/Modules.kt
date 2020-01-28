package com.example.rickandmortyapp.di

import android.app.Application
import androidx.room.Room
import com.apollographql.apollo.ApolloClient
import com.example.rickandmortyapp.MainAcitivityViewModel
import com.example.rickandmortyapp.data.local.Database
import com.example.rickandmortyapp.data.local.dao.CharacterDao
import com.example.rickandmortyapp.data.local.dao.EpisodeDao
import com.example.rickandmortyapp.data.local.datasource.CharactersLocalDataSource
import com.example.rickandmortyapp.data.local.datasource.CharactersLocalDataSourceImpl
import com.example.rickandmortyapp.data.local.datasource.EpisodesLocalDataSource
import com.example.rickandmortyapp.data.local.datasource.EpisodesLocalDataSourceImpl
import com.example.rickandmortyapp.data.remote.CharactersRemoteDataSource
import com.example.rickandmortyapp.data.remote.CharactersRemoteDataSourceImpl
import com.example.rickandmortyapp.data.repository.CharactersRepository
import com.example.rickandmortyapp.data.repository.CharactersRepositoryImpl
import com.example.rickandmortyapp.ui.home.HomeFragmentViewModel
import com.example.rickandmortyapp.ui.home.characterdetail.AboutFragmentViewModel
import com.example.rickandmortyapp.ui.home.characterdetail.CharacterDetailFragmentViewModel
import com.example.rickandmortyapp.ui.home.characterdetail.EpisodeFragmentViewModel
import com.example.rickandmortyapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

    val databaseModules = module {
        fun provideDatabase(application: Application): Database{
            return Room.databaseBuilder(
                application.applicationContext,
                Database::class.java,
                "rickmorty"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        fun provideCharacterDao(database: Database):CharacterDao = database.getCharacterDao()
        fun provideEpisodeDao(database: Database):EpisodeDao = database.getEpisodeDao()

        single { provideDatabase(application = androidApplication()) }
        single { provideCharacterDao(database = get()) }
        single { provideEpisodeDao(database = get()) }
    }

    val networkModules = module{
        fun provideOkHttpClient():OkHttpClient{
            val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build()
        }

        fun provideApolloClient(okHttpClient: OkHttpClient):ApolloClient{
            return ApolloClient.builder().okHttpClient(okHttpClient).serverUrl(Constants.BASE_GRAPHQL_URL).build()
        }

        single {provideOkHttpClient()}
        single { provideApolloClient(okHttpClient = get()) }
    }

    val dataSourceModules = module {
        single<CharactersLocalDataSource>{ CharactersLocalDataSourceImpl(characterDao = get()) }
        single<EpisodesLocalDataSource> { EpisodesLocalDataSourceImpl(episodeDao = get()) }

        single<CharactersRemoteDataSource> { CharactersRemoteDataSourceImpl(apolloClient = get()) }
    }

    val repositoryModules = module{
        single<CharactersRepository>{
            CharactersRepositoryImpl(charactersLocalDataSource = get(),
                charactersRemoteDataSource = get(), episodesLocalDataSource = get())
        }
    }

    val viewModelModules = module {
        viewModel<HomeFragmentViewModel>{
            HomeFragmentViewModel(charactersRepository = get())
        }

        viewModel<CharacterDetailFragmentViewModel>{
            CharacterDetailFragmentViewModel(charactersRepository = get())
        }

        viewModel {
            MainAcitivityViewModel(charactersRepository = get())
        }

        viewModel {
            AboutFragmentViewModel(charactersRepository = get())
        }

        viewModel {
            EpisodeFragmentViewModel(charactersRepository = get())
        }

    }
