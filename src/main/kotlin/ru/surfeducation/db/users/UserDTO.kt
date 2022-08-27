package ru.surfeducation.db.users

import ru.surfeducation.features.register.RegisterReceiveRemote

data class UserDTO(
    val login: String,
    val email: String?,
    val password: String,
    val username: String
) {
    companion object {
        fun newUserDTO(registerReceiveRemote: RegisterReceiveRemote): UserDTO {
            return UserDTO(
                login = registerReceiveRemote.login,
                email = registerReceiveRemote.email,
                password = registerReceiveRemote.password,
                username = ""
            )
        }
    }
}
