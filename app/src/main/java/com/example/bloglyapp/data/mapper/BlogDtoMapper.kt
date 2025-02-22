package com.example.bloglyapp.data.mapper

import com.example.bloglyapp.data.remote.dto.BlogDto
import com.example.bloglyapp.domain.model.Blog

fun BlogDto.toBlog() = Blog(
    id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    contentUrl = contentUrl,
    content = null
)

fun List<BlogDto>.toBlogList() = map {
    it.toBlog()
}