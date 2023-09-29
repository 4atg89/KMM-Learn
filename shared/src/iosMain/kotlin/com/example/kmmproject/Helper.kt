package com.example.kmmproject

import io.ktor.client.HttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

//class HelperKt {

class ClientHelper : KoinComponent {
    private val client: HttpClient by inject()
    fun client(): HttpClient = client
}

fun initKoin() {
    // start Koin
    startKoin {
        modules(appModule())
    }
}
//}