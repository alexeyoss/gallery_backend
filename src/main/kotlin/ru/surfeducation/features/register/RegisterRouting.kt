package ru.surfeducation.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.surfeducation.cache.InMemoryCache
import ru.surfeducation.cache.TokenCache
import ru.surfeducation.features.login.LoginResponseRemote
import ru.surfeducation.utils.isValidEmail
import java.util.*

fun Application.configureRegisterRouting() {

    routing {
        post("/register") {
            val registerController = RegisterController()
            registerController.registerNewUser(call)
        }
    }
}