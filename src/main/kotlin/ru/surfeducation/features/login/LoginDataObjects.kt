package ru.surfeducation.features.login

import kotlinx.serialization.Serializable
import ru.surfeducation.db.users.UserDTO


@Serializable
data class LoginReceiveRemote(
    val phone: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token: String,
    val user_info: UserDTO
)