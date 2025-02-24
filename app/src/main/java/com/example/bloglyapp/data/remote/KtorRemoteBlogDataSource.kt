package com.example.bloglyapp.data.remote

import com.example.bloglyapp.data.util.Constant.GITHUB_URL
import com.example.bloglyapp.data.remote.dto.BlogDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorRemoteBlogDataSource(
    private val client: HttpClient,
) : RemoteBlogDataSource {
    override suspend fun getAllBlogs(): List<BlogDto>? {
        return try {
            val response = client.get(urlString = GITHUB_URL)
            response.body<List<BlogDto>>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}