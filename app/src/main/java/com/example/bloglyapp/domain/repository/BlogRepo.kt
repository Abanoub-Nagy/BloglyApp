package com.example.bloglyapp.domain.repository

import com.example.bloglyapp.domain.model.Blog

interface BlogRepo {
    suspend fun getAllBlogs(): List<Blog>?
}