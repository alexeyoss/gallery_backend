package ru.surfeducation.cache

import ru.surfeducation.features.register.RegisterReceiveRemote

data class TokenCache(
    val login: String,
    val token: String
)

@Deprecated("Use DB entities into the DB package")
object InMemoryCache {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}