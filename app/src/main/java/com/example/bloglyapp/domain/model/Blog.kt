package com.example.bloglyapp.domain.model

data class Blog(
    val id: Int,
    val title: String,
    val contentUrl: String,
    val thumbnailUrl: String,
    val content: String?,
)
