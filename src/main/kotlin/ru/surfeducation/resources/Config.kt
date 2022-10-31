package ru.surfeducation.resources

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

object Config {
    val config = HoconConfigLoader().load("kotlin/ru/surfeducation/resources/config")
}