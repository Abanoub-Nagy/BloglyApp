package com.example.bloglyapp.data.repository

import com.example.bloglyapp.data.local.BlogDao
import com.example.bloglyapp.data.mapper.toBlogEntityList
import com.example.bloglyapp.data.mapper.toBlogList
import com.example.bloglyapp.data.remote.RemoteBlogDataSource
import com.example.bloglyapp.domain.model.Blog
import com.example.bloglyapp.domain.repository.BlogRepo
import com.example.bloglyapp.domain.util.Result

class BlogRepoImpl(
    private val remoteBlogDataSource: RemoteBlogDataSource,
    private val localBlogDataSource: BlogDao
) : BlogRepo {

    override suspend fun getAllBlogs(): Result<List<Blog>> {
        val remoteBlogsResult = remoteBlogDataSource.getAllBlogs()
        return when (remoteBlogsResult) {
            is Result.Success -> {
                remoteBlogsResult.data?.let { blogs ->
                    localBlogDataSource.deleteAllBlogs()
                    localBlogDataSource.insertAllBlogs(blogs.toBlogEntityList())
                    Result.Success(data = blogs.toBlogList())
                } ?: Result.Error("No data available")
            }

            is Result.Error -> {
                val localBlogs = localBlogDataSource.getAllBlogs()
                if (localBlogs.isNotEmpty()) {
                    Result.Error(
                        data = localBlogs.toBlogList(),
                        massage = remoteBlogsResult.massage ?: "failed to fetch data"
                    )
                } else {
                    Result.Error(
                        massage = remoteBlogsResult.massage
                            ?: "failed to fetch data and no local data available"
                    )
                }
            }
        }
    }

}