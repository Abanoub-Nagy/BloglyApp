package com.example.bloglyapp.presentation.blog_content

import com.example.bloglyapp.domain.model.Blog

data class BlogContentState(
    val errorMassage: String? = null,
    val isLoading: Boolean = false,
    val blog: Blog? = null
)
