package com.example.games

import com.example.games.model.Game
import com.example.games.model.ShortGame
import com.example.network.GameService
import com.example.network.model.GameDTO
import com.example.network.model.ShortGameDTO

interface GameRepository {

    suspend fun games(): List<ShortGame>
    suspend fun game(id: Int): Game
}

internal class GameRepositoryImpl(private val api: GameService) : GameRepository {
    override suspend fun games(): List<ShortGame> = api.games().map { it.map() }

    override suspend fun game(id: Int): Game = api.game(id).map()
    private fun GameDTO.map() = Game(
        short = ShortGame(
            id = id,
            title = title,
            thumbnail = thumbnail,
            shortDescription = shortDescription,
            gameUrl = gameUrl,
            genre = genre,
            publisher = publisher,
            developer = developer,
            releaseDate = releaseDate,
            freeToGameProfileUrl = freeToGameProfileUrl,
        )
    )

    private fun ShortGameDTO.map() = ShortGame(
        id = id,
        title = title,
        thumbnail = thumbnail,
        shortDescription = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate,
        freeToGameProfileUrl = freeToGameProfileUrl,
    )


}