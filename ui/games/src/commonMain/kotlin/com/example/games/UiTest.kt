package com.example.games

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.transition.NavTransition
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.network.GameService
import org.koin.compose.getKoin

@Composable
fun UiTest() {
    val text = remember { mutableStateOf("UI Text") }
    Text(text = text.value)
    val koin = getKoin()
    LaunchedEffect(Unit) {
        text.value = koin.get<GameService>()::class.toString()
    }
    NavTransition()
}