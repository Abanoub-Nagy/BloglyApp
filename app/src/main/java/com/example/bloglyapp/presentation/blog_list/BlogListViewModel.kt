package com.example.bloglyapp.presentation.blog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloglyapp.domain.repository.BlogRepo
import com.example.bloglyapp.domain.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
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

    private val _event = Channel<BlogListEvent>()
    val event = _event.receiveAsFlow()

    private fun getAllBlogs() {
        viewModelScope.launch {
            val result = blogRepository.getAllBlogs()
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            blogs = result.data.orEmpty().reversed(),
                            errorMassage = null
                        )
                    }
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(
                            blogs = result.data.orEmpty().reversed(),
                            errorMassage = result.massage
                        )
                    }
                    result.massage?.let {
                        _event.send(BlogListEvent.Error(it))
                    }
                }
            }
        }
    }
}