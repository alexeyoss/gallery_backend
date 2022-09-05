package ru.surfeducation.features.login

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {

    routing {
        post("/login") {
            val loginController = LoginController()
            loginController.performLogin(call)
        }
    }
}