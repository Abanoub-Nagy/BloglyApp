package com.example.bloglyapp.data.repository

import com.example.bloglyapp.data.mapper.toBlogList
import com.example.bloglyapp.data.remote.RemoteBlogDataSource
import com.example.bloglyapp.domain.model.Blog
import com.example.bloglyapp.domain.repository.BlogRepo

class BlogRepoImpl(
    private val remoteDataSource: RemoteBlogDataSource
) : BlogRepo {


    override suspend fun getAllBlogs(): List<Blog>? {
        return remoteDataSource.getAllBlogs()?.toBlogList()
    }

}