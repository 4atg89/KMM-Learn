package com.example.kmmproject

import com.example.network.di.networkModule
import org.koin.dsl.module

fun appModule() = module {
    includes(networkModule)
}