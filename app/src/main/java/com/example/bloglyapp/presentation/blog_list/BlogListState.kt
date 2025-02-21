package com.example.bloglyapp.presentation.blog_list

import com.example.bloglyapp.domain.model.Blog

data class BlogListState(
    val isLoading: Boolean = false,
    val blogs: List<Blog> = emptyList(),
    val errorMassage: String? = null,
)