package ru.surfeducation.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.surfeducation.db.users.UserDTO
import ru.surfeducation.db.users.Users
import ru.surfeducation.utils.validators.RegisterValidator

class RegisterController {


    suspend fun registerNewUser(call: ApplicationCall) {
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        val registerValidator = RegisterValidator(registerReceiveRemote)

        if (!registerValidator.validate()) {
            call.respond(HttpStatusCode.BadRequest, "Phone is not valid. The phone must be +71234567890")
        }

        val userDTO = Users.fetchUser(phone = registerReceiveRemote.phone)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exist")
        } else {

            try {
                Users.insert(UserDTO.newUserDTO(registerReceiveRemote))
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exist")
            }

            call.respond(HttpStatusCode.OK, "Successful registered")
        }

    }
}