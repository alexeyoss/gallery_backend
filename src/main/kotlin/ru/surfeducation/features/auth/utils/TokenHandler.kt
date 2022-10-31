package ru.surfeducation.features.auth.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import ru.surfeducation.db.tokens.Tokens
import java.util.*

object TokenHandler {

    private val baseAlgo by lazy { Algorithm.HMAC256("secret") }
    private val expDate by lazy { Date(System.currentTimeMillis() + 60000) }

    fun createNewToken(): String {
        return JWT.create()
            .withExpiresAt(expDate)
            .sign(baseAlgo)
    }

    fun checkTokenDueDate(token: String): Boolean {
        val userToken = JWT.require(baseAlgo).build().verify(token).token
        return Tokens.isTokenExist(userToken)
    }
}