package ru.surfeducation.db.tokens

import java.util.*

typealias TokenString = String

data class TokenDTO(
    val id: String,
    val phone: String,
    val token: UUID
) {
    companion object {
        fun newTokenDTO(receivePhone: String): Pair<TokenDTO, UUID> {
            val token = UUID.randomUUID()
            return Pair(
                TokenDTO(
                    id = UUID.randomUUID().toString(),
                    phone = receivePhone,
                    token = token
                ), token
            )
        }
    }
}

