package ru.surfeducation.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.surfeducation.cache.InMemoryCache
import ru.surfeducation.cache.TokenCache
import ru.surfeducation.db.tokens.TokenDTO
import ru.surfeducation.db.tokens.Tokens
import ru.surfeducation.db.users.UserDTO
import ru.surfeducation.db.users.Users
import ru.surfeducation.utils.isValidEmail
import java.util.*

class RegisterController {

    suspend fun registerNewUser(call: ApplicationCall) {
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }

        val userDTO = Users.fetchUser(login = registerReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exist")
        } else {

            try {
                Users.insert(UserDTO.newUserDTO(registerReceiveRemote))
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exist")
            }

            val tokenDTO = TokenDTO.newTokenDTO(registerReceiveRemote.login)
            Tokens.insert(tokenDTO.first)
            call.respond(RegisterResponseRemote(token = tokenDTO.second))
        }

    }
}