package ru.surfeducation.db.users

import kotlinx.serialization.Serializable
import ru.surfeducation.features.register.RegisterReceiveRemote
import java.util.*

@Serializable
data class UserDTO(
    val phone: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val city: String,
    val about: String
) {
    companion object {
        fun newUserDTO(registerReceiveRemote: RegisterReceiveRemote): UserDTO {
            return UserDTO(
                phone = registerReceiveRemote.phone,
                password = registerReceiveRemote.password,
                email = registerReceiveRemote.email ?: "",
                firstName = registerReceiveRemote.firstName ?: "",
                lastName = registerReceiveRemote.lastName ?: "",
                avatar = registerReceiveRemote.avatar ?: "",
                city = registerReceiveRemote.city ?: "",
                about = registerReceiveRemote.about ?: ""
            )
        }
    }
}
