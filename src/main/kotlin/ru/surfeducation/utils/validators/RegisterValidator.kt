package ru.surfeducation.utils.validators

import ru.surfeducation.features.register.RegisterReceiveRemote

class RegisterValidator(
    private val registerReceiveRemote: RegisterReceiveRemote
) : BaseValidator {

    // TODO rebuild the regexp mask cause it approve incorrect formats of phone
    private fun isValidPhone(): Boolean {
        return phoneRegexp.matches(registerReceiveRemote.phone)
    }

    override fun validate(): Boolean = isValidPhone()

    companion object {
        @JvmStatic
        val phoneRegexp = "\\+7\\d{10}\$".toRegex()
    }
}
