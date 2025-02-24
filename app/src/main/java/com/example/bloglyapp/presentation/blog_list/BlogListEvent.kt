package com.example.bloglyapp.presentation.blog_list

sealed interface BlogListEvent {
    data class Error(val massage: String) : BlogListEvent
}