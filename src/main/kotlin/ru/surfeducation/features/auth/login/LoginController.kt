package ru.surfeducation.features.auth.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.surfeducation.db.tokens.TokenDTO
import ru.surfeducation.db.tokens.Tokens
import ru.surfeducation.db.users.UserInfoDTO
import ru.surfeducation.db.users.Users
import ru.surfeducation.features.auth.LoginReceiveRemote
import ru.surfeducation.features.auth.LoginResponseRemote

class LoginController {

    suspend fun performLogin(call: ApplicationCall) {
        val loginReceiveRemote = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(loginReceiveRemote.phone)

        if (userDTO == null) call.respond(HttpStatusCode.Conflict, "User not found")
        else {
            if (userDTO.password == loginReceiveRemote.password) {
                val oldTokenDTO = Tokens.fetchTokenByPhone(loginReceiveRemote.phone)

                if (oldTokenDTO != null) {
                    call.respond(
                        LoginResponseRemote(
                            token = oldTokenDTO.token, user_info = UserInfoDTO(
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
                    val newTokenDTO = TokenDTO.newTokenDTO(loginReceiveRemote.phone)

                    Tokens.insert(newTokenDTO)

                    call.respond(
                        LoginResponseRemote(
                            token = newTokenDTO.token, user_info = UserInfoDTO(
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
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }

}