package ru.surfeducation

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import ru.surfeducation.features.login.configureLoginRouting
import ru.surfeducation.features.register.configureRegisterRouting
import ru.surfeducation.plugins.configureSerialization

fun main() {

    Database.connect(
        url = "jdbc:postgresql://localhost:5432/surf_education",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Somali050796!"
    )

    embeddedServer(Netty, port = System.getenv("PORT").toInt(), host = "0.0.0.0") {
        configureSerialization()

        configureLoginRouting()
        configureRegisterRouting()
    }.start(wait = true)
}
