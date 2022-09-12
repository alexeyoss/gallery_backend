package ru.surfeducation.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.addJSONSerialization() {
    install(ContentNegotiation) {
        json()
    }
}