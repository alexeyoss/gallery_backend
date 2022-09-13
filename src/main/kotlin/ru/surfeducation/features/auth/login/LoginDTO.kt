package ru.surfeducation.features.auth

import kotlinx.serialization.Serializable
import ru.surfeducation.db.users.UserInfoDTO


@Serializable
data class LoginReceiveRemote(
    val phone: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token: String,
    val user_info: UserInfoDTO
)