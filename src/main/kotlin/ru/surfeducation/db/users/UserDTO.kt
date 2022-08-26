package ru.surfeducation.db.users

data class UserDTO(
    val login: String,
    val email: String?,
    val password: String,
    val username: String
)
