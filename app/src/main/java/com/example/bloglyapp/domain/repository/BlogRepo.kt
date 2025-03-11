package com.example.bloglyapp.domain.repository

import com.example.bloglyapp.domain.model.Blog
import com.example.bloglyapp.domain.util.Result

interface BlogRepo {
    suspend fun getAllBlogs(): Result<List<Blog>>
    suspend fun getBlogById(id: Int): Result<Blog>
}