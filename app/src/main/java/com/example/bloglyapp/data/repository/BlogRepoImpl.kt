package com.example.bloglyapp.data.repository

import com.example.bloglyapp.data.local.BlogDao
import com.example.bloglyapp.data.mapper.toBlogEntityList
import com.example.bloglyapp.data.mapper.toBlogList
import com.example.bloglyapp.data.remote.RemoteBlogDataSource
import com.example.bloglyapp.domain.model.Blog
import com.example.bloglyapp.domain.repository.BlogRepo

class BlogRepoImpl(
    private val remoteBlogDataSource: RemoteBlogDataSource,
    private val localBlogDataSource: BlogDao
) : BlogRepo {

    override suspend fun getAllBlogs(): List<Blog>? {
        val remoteBlogs = remoteBlogDataSource.getAllBlogs()
        return if (remoteBlogs != null) {
            localBlogDataSource.deleteAllBlogs()
            localBlogDataSource.insertAllBlogs(remoteBlogs.toBlogEntityList())
            remoteBlogs.toBlogList()
        } else {
            val localBlogs = localBlogDataSource.getAllBlogs()
            if (localBlogs.isNotEmpty()) {
                localBlogs.toBlogList()
            } else {
                null
            }
        }
    }

}