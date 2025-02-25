package com.example.bloglyapp.presentation.blog_content

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.bloglyapp.presentation.navigation.Route

class BlogContentViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val blogId = savedStateHandle.toRoute<Route.BlogContentScreen>().blogId
}