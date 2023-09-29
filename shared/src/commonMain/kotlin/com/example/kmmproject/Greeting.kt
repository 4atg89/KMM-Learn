package com.example.kmmproject

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class Test {

    suspend fun httpTest(): List<ShortGame> {

        val client = HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            defaultRequest {
                url("https://www.freetogame.com/")
            }
        }
        val response = client.get("api/games") {

        }
        client.close()

        return response.body()
    }
}