package com.example.bloglyapp.presentation.blog_list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.bloglyapp.domain.model.Blog
import com.example.bloglyapp.presentation.blog_list.component.BlogCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun BlogListScreen(
    modifier: Modifier = Modifier, state: BlogListState, event: Flow<BlogListEvent>,
    onBlogCardClick: (Int) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        event.collect { event ->
            when (event) {
                is BlogListEvent.Error -> {
                    Toast.makeText(context, event.massage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        BlogListTopBar()
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Adaptive(minSize = 300.dp),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(state.blogs) { blog ->
                BlogCard(
                    blog = blog,
                    modifier = Modifier.clickable {
                        onBlogCardClick(blog.id)
                    }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlogListTopBar(
    modifier: Modifier = Modifier,
) {
    TopAppBar(windowInsets = WindowInsets(0), modifier = modifier, title = {
        Text(text = "Android Blogs")
    })
}

@PreviewScreenSizes
@Composable
fun BlogListScreenPreview() {
    val dummyBlog = listOf(
        Blog(
            id = 1,
            title = "State Management in Compose",
            thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ74IPrlUuveQgP_-YFJPH2n8liAyyybZxNnA&s",
            contentUrl = "",
            content = null
        )
    )
    BlogListScreen(
        state = BlogListState(blogs = dummyBlog),
        event = emptyFlow(),
        onBlogCardClick = {}
    )
}