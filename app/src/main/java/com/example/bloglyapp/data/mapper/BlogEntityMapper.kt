package com.example.bloglyapp.data.mapper

import com.example.bloglyapp.data.local.entity.BlogEntity
import com.example.bloglyapp.domain.model.Blog

fun BlogEntity.toBlog(
    content: String? = null
) = Blog(
    id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    contentUrl = contentUrl,
    content = content
)

fun List<BlogEntity>.toBlogList() = map {
    it.toBlog()
}