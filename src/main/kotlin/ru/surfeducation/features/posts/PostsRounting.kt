package ru.surfeducation.features.posts

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configurePostsRouting() {

    routing {
        post("/posts") {
            val postsController = PostsController()
            postsController.getPosts(call)
        }
    }
}