package com.example.bloglyapp.data.remote

import com.example.bloglyapp.data.remote.dto.BlogDto
import com.example.bloglyapp.domain.util.Result

interface RemoteBlogDataSource {
    suspend fun getAllBlogs(): Result<List<BlogDto>>
    suspend fun fetchBlogContent(url: String): Result<String>
}