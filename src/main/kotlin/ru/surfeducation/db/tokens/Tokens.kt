package ru.surfeducation.db.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table("tokens") {
    private val id = Tokens.varchar("id", 50)
    private val phone = Tokens.varchar("phone", 16)
    private val token = Tokens.uuid("token")
//    private val tokenLifetime : Timestamp

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[id] = tokenDTO.id
                it[phone] = tokenDTO.phone
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchToken(phone: String): TokenDTO? {
        return try {
            val tokenDTO = transaction {
                Tokens.select {
                    Tokens.phone.eq(phone)
                }.single()
            }
            TokenDTO(
                id = tokenDTO[id],
                phone = tokenDTO[Tokens.phone],
                token = tokenDTO[token]
            )
        } catch (e: Exception) {
            return null
        }
    }
}