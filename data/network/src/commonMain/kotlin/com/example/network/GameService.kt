package com.example.network

import com.example.network.model.GameDTO
import com.example.network.model.ShortGameDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface GameService {
    suspend fun games(): List<ShortGameDTO>
    suspend fun game(id: Int): GameDTO
}

class GameServiceImpl(private val api: HttpClient) : GameService {

    override suspend fun games(): List<ShortGameDTO> =
        api.get(NetworkContract.Games.GAMES).body()

    override suspend fun game(id: Int): GameDTO =
        api.get(NetworkContract.Games.GAME) {
            url { parameters.append("id", id.toString()) }
        }.body()
}