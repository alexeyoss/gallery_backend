package ru.surfeducation.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.surfeducation.db.tokens.TokenDTO
import ru.surfeducation.db.tokens.Tokens
import ru.surfeducation.db.users.UserInfoDTO
import ru.surfeducation.db.users.Users

class LoginController {

    suspend fun performLogin(call: ApplicationCall) {
        val loginReceiveRemote = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(loginReceiveRemote.phone)

        if (userDTO == null) call.respond(HttpStatusCode.Conflict, "User not found")
        else {
            if (userDTO.password == loginReceiveRemote.password) {

                val tokenDTO = TokenDTO.newTokenDTO(loginReceiveRemote.phone)

                Tokens.insert(tokenDTO.first)

                val userInfoDTO = call.respond(
                    LoginResponseRemote(
                        token = tokenDTO.second, user_info = UserInfoDTO(
                            phone = userDTO.phone,
                            email = userDTO.email,
                            firstName = userDTO.firstName,
                            lastName = userDTO.lastName,
                            avatar = userDTO.avatar,
                            city = userDTO.city,
                            about = userDTO.about
                        )
                    )
                )
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }

}