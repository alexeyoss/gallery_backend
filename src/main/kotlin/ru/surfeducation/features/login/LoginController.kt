package ru.surfeducation.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.surfeducation.cache.InMemoryCache
import ru.surfeducation.cache.TokenCache
import ru.surfeducation.db.tokens.TokenDTO
import ru.surfeducation.db.tokens.Tokens
import ru.surfeducation.db.users.Users
import java.util.*

class LoginController {

    suspend fun performLogin(call: ApplicationCall) {
        val loginReceiveRemote = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(loginReceiveRemote.login)

        if (userDTO == null) call.respond(HttpStatusCode.Conflict, "User not found")
        else {
            if (userDTO.password == loginReceiveRemote.password) {

                val tokenDTO = TokenDTO.newTokenDTO(loginReceiveRemote.login)

                Tokens.insert(tokenDTO.first)
                call.respond(LoginResponseRemote(token = tokenDTO.second))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }

}