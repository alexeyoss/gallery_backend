package ru.surfeducation.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.surfeducation.cache.InMemoryCache
import ru.surfeducation.cache.TokenCache
import java.util.UUID

fun Application.configureLoginRouting() {

    routing {
        post("/login") {
            val loginController = LoginController()
            loginController.performLogin(call)
        }
    }
}