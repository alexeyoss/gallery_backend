package ru.surfeducation.features.posts

import kotlinx.serialization.Serializable

@Serializable
data class PostsResponseRemote(
    val id: Int,
    val title: String,
    val content: String,
    val photoUrl: String,
    val publicationDate: String
)