package com.example.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class TestRequest(private val client: HttpClient) {
    @OptIn(ExperimentalSerializationApi::class)
    suspend fun httpTest(): List<ShortGame> {

//        val client = HttpClient() {
//
//            install(ContentNegotiation) {
//                json(Json {
//                    explicitNulls = false
//                    ignoreUnknownKeys = true
//                    isLenient = true
//                    coerceInputValues = true
//                })
//            }
//            defaultRequest {
//                url("https://www.freetogame.com")
//            }
//        }
        val response = client.get(NetworkContract.Games.GAMES)
        client.close()

        return response.body()
    }
}