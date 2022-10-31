package ru.surfeducation.db.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table("users") {
    private val id = Users.integer("id").autoIncrement()
    private val phone = Users.varchar("phone", 16)
    private val password = Users.varchar("password", 32)
    private val email = Users.varchar("email", 32)
    private val first_name = Users.varchar("first_name", 32)
    private val last_name = Users.varchar("last_name", 32)
    private val avatar = Users.varchar("avatar", 128)
    private val city = Users.varchar("city", 32)
    private val about = Users.varchar("about", 256)

    override val primaryKey = PrimaryKey(id)
    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[phone] = userDTO.phone
                it[password] = userDTO.password
                it[email] = userDTO.email
                it[first_name] = userDTO.firstName
                it[last_name] = userDTO.lastName
                it[avatar] = userDTO.avatar
                it[city] = userDTO.city
                it[about] = userDTO.about
            }
        }
    }

    fun fetchUser(phone: String): UserDTO? {
        return try {
            val userModel = transaction {
                Users.select {
                    Users.phone.eq(phone)
                }.single()
            }
            UserDTO(
                phone = userModel[Users.phone],
                password = userModel[password],
                email = userModel[email],
                firstName = userModel[first_name],
                lastName = userModel[last_name],
                avatar = userModel[avatar],
                city = userModel[city],
                about = userModel[about]
            )
        } catch (e: Exception) {
            return null
        }
    }
}