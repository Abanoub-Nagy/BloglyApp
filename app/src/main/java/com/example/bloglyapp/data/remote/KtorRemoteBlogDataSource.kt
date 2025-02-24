package com.example.bloglyapp.data.remote

import com.example.bloglyapp.data.remote.dto.BlogDto
import com.example.bloglyapp.data.util.Constant.GITHUB_URL
import com.example.bloglyapp.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.net.UnknownHostException

class KtorRemoteBlogDataSource(
    private val client: HttpClient,
) : RemoteBlogDataSource {
    override suspend fun getAllBlogs(): Result<List<BlogDto>> {
        return try {
            val response = client.get(urlString = GITHUB_URL)
            val blogs = response.body<List<BlogDto>>()
            Result.Success(blogs)
        } catch (e: UnknownHostException) {
            Result.Error("Network Error. please check your internet connection")
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.message ?: "Something went wrong. ${e.message}")
        }
    }
}