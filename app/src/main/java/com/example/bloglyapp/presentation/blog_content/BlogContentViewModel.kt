package com.example.bloglyapp.presentation.blog_content

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.bloglyapp.domain.repository.BlogRepo
import com.example.bloglyapp.domain.util.Result
import com.example.bloglyapp.presentation.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogContentViewModel(
    savedStateHandle: SavedStateHandle,
    private val blogRepository: BlogRepo
) : ViewModel() {
    val blogId = savedStateHandle.toRoute<Route.BlogContentScreen>().blogId

    private val _state = MutableStateFlow(BlogContentState())
    val state = _state
        .onStart {
            getBlogById()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    fun onAction(action: BlogContentAction) {
        when (action) {
            BlogContentAction.Refresh -> {
                getBlogById()
            }
        }
    }

    private fun getBlogById() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val result = blogRepository.getBlogById(blogId)
            when (result) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            blog = result.data,
                            errorMassage = null,
                            isLoading = false
                        )
                    }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(
                            blog = result.data,
                            errorMassage = result.massage,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}