package com.example.bloglyapp.presentation.blog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloglyapp.data.mapper.toBlogList
import com.example.bloglyapp.data.remote.HttpClientFactory
import com.example.bloglyapp.data.remote.KtorRemoteBlogDataSource
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogListViewModel : ViewModel() {
    private val _state = MutableStateFlow(BlogListState())
    val state = _state
        .onStart {
            getAllBlogs()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )
    private val client = HttpClientFactory.create(OkHttp.create())
    private val remoteDataSource = KtorRemoteBlogDataSource(client)

    private fun getAllBlogs() {
        viewModelScope.launch {
            val blogs = remoteDataSource.getAllBlogs()
            if (blogs != null) {
                _state.update {
                    it.copy(blogs = blogs.toBlogList())
                }
            }
        }
    }
}