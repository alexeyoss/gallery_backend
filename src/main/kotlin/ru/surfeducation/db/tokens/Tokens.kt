package ru.surfeducation.db.tokens

import org.jetbrains.exposed.sql.Table
import java.util.*

object Tokens : Table("tokens") {
    private val id = Tokens.varchar("id", 25)
    private val login = Tokens.varchar("login", 25)
    private val token = Tokens.varchar("token", 50)

}