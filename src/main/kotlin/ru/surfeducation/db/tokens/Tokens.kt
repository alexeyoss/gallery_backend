package ru.surfeducation.db.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table("tokens") {
    private val id = Tokens.integer("id").autoIncrement()
    private val phone = Tokens.varchar("phone", 16)
    private val token = Tokens.varchar("token", 128)

    override val primaryKey = PrimaryKey(id)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[phone] = tokenDTO.phone
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchTokenByPhone(phone: String): TokenDTO? {
        return try {
            val tokenDTO = transaction {
                Tokens.select {
                    Tokens.phone.eq(phone)
                }.single()
            }
            TokenDTO(
                phone = tokenDTO[Tokens.phone],
                token = tokenDTO[token]
            )
        } catch (e: Exception) {
            return null
        }
    }

    fun isTokenExist(token: String): Boolean {
        val dbToken = transaction {
            Tokens.select {
                Tokens.token.eq(token)
            }.single()
        }
        return dbToken.equals(token)
    }

}