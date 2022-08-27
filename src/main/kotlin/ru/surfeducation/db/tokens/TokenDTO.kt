package ru.surfeducation.db.tokens

import java.util.*

typealias TokenString = String
data class TokenDTO(
    val id: String, val login: String, val token: String
) {
    companion object {
        fun newTokenDTO(receiveModelLogin: String): Pair<TokenDTO, TokenString> {
            val token = UUID.randomUUID().toString()
            return Pair(
                TokenDTO(
                    id = UUID.randomUUID().toString(), login = receiveModelLogin, token = token
                ),
                token
            )
        }
    }
}

