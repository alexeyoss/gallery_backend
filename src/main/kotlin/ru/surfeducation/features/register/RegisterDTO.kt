package ru.surfeducation.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val phone: String,
    val password: String,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?,
    val city: String?,
    val about: String?
)