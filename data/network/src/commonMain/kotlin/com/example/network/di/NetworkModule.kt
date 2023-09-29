package com.example.network.di

import com.example.network.TestRequest
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        Json {
            explicitNulls = false
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
    }
    single {
        HttpClient {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 20000
                requestTimeoutMillis = 20000
            }
            install(ContentNegotiation) { json(get()) }
            defaultRequest { url("https://www.freetogame.com") }
        }
    }
    single { TestRequest(get()) }
}
