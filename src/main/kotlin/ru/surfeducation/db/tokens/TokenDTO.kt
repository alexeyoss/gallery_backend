package ru.surfeducation.db.tokens

import ru.surfeducation.features.auth.utils.TokenHandler

data class TokenDTO(
    val phone: String,
    val token: String
) {
    companion object {
        fun newTokenDTO(receivePhone: String): TokenDTO {
            return TokenDTO(
                phone = receivePhone,
                token = TokenHandler.createNewToken()
            )
        }
    }
}

