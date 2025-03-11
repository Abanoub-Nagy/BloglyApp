package com.example.bloglyapp.data.repository

import com.example.bloglyapp.data.local.BlogDao
import com.example.bloglyapp.data.local.entity.BlogContentEntity
import com.example.bloglyapp.data.mapper.toBlog
import com.example.bloglyapp.data.mapper.toBlogEntityList
import com.example.bloglyapp.data.mapper.toBlogList
import com.example.bloglyapp.data.remote.RemoteBlogDataSource
import com.example.bloglyapp.domain.model.Blog
import com.example.bloglyapp.domain.repository.BlogRepo
import com.example.bloglyapp.domain.util.Result

class BlogRepoImpl(
    private val remoteBlogDataSource: RemoteBlogDataSource, private val localBlogDataSource: BlogDao
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

    override suspend fun getBlogById(id: Int): Result<Blog> {
        val blogEntity = localBlogDataSource.getBlogById(id)
            ?: return Result.Error("Blog not found in local database")
        val contentResult = remoteBlogDataSource.fetchBlogContent(blogEntity.contentUrl)
        return when (contentResult) {
            is Result.Success -> {
                val blogContentEntity = BlogContentEntity(
                    blogId = blogEntity.id, content = contentResult.data ?: ""
                )
                localBlogDataSource.insertBlogContent(blogContentEntity)
                Result.Success(
                    data = blogEntity.toBlog(contentResult.data)
                )
            }

            is Result.Error -> {
                val contentEntity = localBlogDataSource.getBlogContentById(id)
                if (contentEntity != null) {
                    Result.Success(
                        data = blogEntity.toBlog(contentEntity.content)
                    )
                } else {
                    Result.Error(
                        massage = contentResult.massage
                            ?: "failed to fetch blog content. ${contentResult.massage}"
                    )
                }
            }

        }
    }
}