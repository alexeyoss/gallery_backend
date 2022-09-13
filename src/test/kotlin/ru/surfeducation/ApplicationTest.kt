package ru.surfeducation

import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import ru.surfeducation.features.auth.configureAuthRouting

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureAuthRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}