package ru.surfeducation

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import ru.surfeducation.features.login.configureLoginRouting
import ru.surfeducation.features.register.configureRegisterRouting
import ru.surfeducation.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5423/surf_education", driver = "org.postgresql.Driver",
    password = "Somali050796!")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureSerialization()

        configureRouting()
        configureLoginRouting()
        configureRegisterRouting()
    }.start(wait = true)
}
