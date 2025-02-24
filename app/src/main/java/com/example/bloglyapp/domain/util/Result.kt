package com.example.bloglyapp.domain.util

sealed class Result<T>(
    val data : T? = null,
    val massage : String? = null
) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(massage: String, data: T? = null) : Result<T>(data, massage)

}