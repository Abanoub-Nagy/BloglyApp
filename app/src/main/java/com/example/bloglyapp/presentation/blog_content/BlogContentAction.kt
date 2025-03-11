package com.example.bloglyapp.presentation.blog_content

sealed interface BlogContentAction {
    data object Refresh : BlogContentAction
}