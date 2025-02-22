package com.example.bloglyapp.data.remote

import com.example.bloglyapp.data.remote.dto.BlogDto

interface RemoteBlogDataSource {
    suspend fun getAllBlogs(): List<BlogDto>?
}