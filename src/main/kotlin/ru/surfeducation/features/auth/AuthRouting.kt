package ru.surfeducation.features.auth

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.surfeducation.features.auth.login.LoginController
import ru.surfeducation.features.auth.logout.LogoutController

fun Application.configureAuthRouting() {

    routing {
        post("/auth/login") {
            val loginController = LoginController()
            loginController.performLogin(call)
        }

        post("/auth/logout") {
            val logoutController = LogoutController()
            logoutController.performLogout(call)
        }
    }
}