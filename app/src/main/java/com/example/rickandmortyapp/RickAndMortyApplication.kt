package com.example.rickandmortyapp

import android.app.Application
import com.example.rickandmortyapp.di.*
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initKoin()
    }

    private fun initStetho(){
        Stetho.initializeWithDefaults(this)
    }

    private fun initKoin(){
        val modules= listOf(databaseModules, viewModelModules, dataSourceModules, repositoryModules, networkModules)
        startKoin{
            androidContext(this@RickAndMortyApplication)
            modules(modules)
        }

    }
}