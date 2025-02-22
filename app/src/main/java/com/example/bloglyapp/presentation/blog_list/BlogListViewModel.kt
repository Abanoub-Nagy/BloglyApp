package com.example.bloglyapp.presentation.blog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloglyapp.domain.repository.BlogRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogListViewModel(
    private val blogRepository: BlogRepo
) : ViewModel() {
    private val _state = MutableStateFlow(BlogListState())
    val state = _state.onStart {
            getAllBlogs()
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value
        )


    private fun getAllBlogs() {
        viewModelScope.launch {
            val blogs = blogRepository.getAllBlogs()
            if (blogs != null) {
                _state.update {
                    it.copy(blogs = blogs)
                }
            }
        }
    }
}