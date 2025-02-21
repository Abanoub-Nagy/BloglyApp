package com.example.bloglyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.bloglyapp.domain.model.Blog
import com.example.bloglyapp.presentation.blog_list.BlogListScreen
import com.example.bloglyapp.presentation.blog_list.BlogListState
import com.example.bloglyapp.presentation.theme.BloglyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloglyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val dummyBlog = listOf(
                        Blog(
                            id = 1,
                            title = "State Management in Compose",
                            thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5A6a9lBAJh219UyHrYvxYB67kwSMQn0ACEQ&s",
                            contentUrl = "",
                            content = null
                        )
                    )
                    BlogListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = BlogListState(blogs = dummyBlog)
                    )
                }
            }
        }
    }
}
