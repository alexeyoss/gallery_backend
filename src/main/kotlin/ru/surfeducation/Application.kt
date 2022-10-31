package ru.surfeducation

import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.surfeducation.features.auth.configureAuthRouting
import ru.surfeducation.features.register.configureRegisterRouting
import ru.surfeducation.plugins.configureJSONSerialization

fun main() {

    // TODO refactoring
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/surf_education",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Somali050796!"
    )

    embeddedServer(CIO, host = "0.0.0.0", port = 8080) {
        configureJSONSerialization()

        configureAuthRouting()
        configureRegisterRouting()
//        configurePostsRouting() TODO implement logic
    }.start(wait = true)
}
