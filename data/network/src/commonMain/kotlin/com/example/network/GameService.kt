package com.example.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface GameService {
    suspend fun games(): List<ShortGame>
    suspend fun game(id: Int): Game
}

class GameServiceImpl(private val api: HttpClient) : GameService {

    override suspend fun games(): List<ShortGame> =
        api.get(NetworkContract.Games.GAMES).body()

    override suspend fun game(id: Int): Game =
        api.get(NetworkContract.Games.GAME) {
            url { parameters.append("id", id.toString()) }
        }.body()
}