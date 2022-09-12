package ru.surfeducation.db.users

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDTO(
    val phone: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val city: String,
    val about: String
)
