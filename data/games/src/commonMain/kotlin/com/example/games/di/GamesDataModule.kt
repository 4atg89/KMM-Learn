package com.example.games.di

import com.example.games.GameRepository
import com.example.games.GameRepositoryImpl
import com.example.network.di.networkModule
import org.koin.dsl.module


val gamesDataModule = module {
    includes(networkModule)
    single<GameRepository> { GameRepositoryImpl(get()) }
}
