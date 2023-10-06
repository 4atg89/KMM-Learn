package com.example.kmmproject

import com.example.games.di.gamesDataModule
import org.koin.dsl.module

fun appModule() = module {
    includes(gamesDataModule)
    
}