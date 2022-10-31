package ru.surfeducation.features.auth.logout

import io.ktor.server.application.*
import ru.surfeducation.db.tokens.Tokens
import java.util.*

class LogoutController {
    suspend fun performLogout(call: ApplicationCall) {
        val userToken = UUID.fromString(
            call.request.headers["Authorization"]?.apply {
                replace("Token ", "")
            }
        )
    }
}