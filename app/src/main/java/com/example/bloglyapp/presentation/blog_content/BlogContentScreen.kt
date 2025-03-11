package com.example.bloglyapp.presentation.blog_content

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.jeziellago.compose.markdowntext.MarkdownText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogContentScreen(
    onBackClick: () -> Unit,
    state: BlogContentState,
    onAction: (BlogContentAction) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        BlogContentTopBar(
            onBackClick = onBackClick,
            title = state.blog?.title,
            scrollBehavior = scrollBehavior
        )
        if (state.isLoading) {
            LoadingContent(
                modifier = Modifier.fillMaxSize()
            )
        } else {
            when {
                state.errorMassage != null -> {
                    ErrorContent(
                        modifier = Modifier
                            .fillMaxSize(),
                        errorMessage = state.errorMassage,
                        onRefreshClick = { onAction(BlogContentAction.Refresh) }
                    )
                }

                else -> {
                    MainContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                            .verticalScroll(rememberScrollState()),
                        blogContent = state.blog?.content
                    )
                }
            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier, blogContent: String?
) {
    Column(
        modifier = modifier
    ) {
        MarkdownText(
            markdown = blogContent ?: "",
            linkColor = MaterialTheme.colorScheme.secondary,
            isTextSelectable = true,
            syntaxHighlightColor = MaterialTheme.colorScheme.surfaceVariant,
            syntaxHighlightTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlogContentTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    title: String?,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        windowInsets = WindowInsets(0),
        modifier = modifier,
        title = {
            Text(
                text = title ?: "Blog Content", maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate Back"
                )
            }
        }
    )
}

@Composable
private fun ErrorContent(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onRefreshClick
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = errorMessage,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}

/*//@Composable
//private fun LoadingContent(
//    modifier: Modifier = Modifier,
//    shimmerBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
//    shimmerHighlightColor: Color = MaterialTheme.colorScheme.surface,
//    animationDuration: Int = 1000
//) {
//    Column(modifier = modifier) {
//        // Header with avatar and text placeholders
//        LoadingHeader(shimmerBackgroundColor, shimmerHighlightColor, animationDuration)
//
//        // List of placeholders
//        LoadingList(shimmerBackgroundColor, shimmerHighlightColor, animationDuration)
//    }
//}
//
//@Composable
//private fun LoadingHeader(
//    shimmerBackgroundColor: Color,
//    shimmerHighlightColor: Color,
//    animationDuration: Int
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(10.dp)
//    ) {
//        // Avatar placeholder
//        ShimmerBox(
//            modifier = Modifier
//                .size(80.dp)
//                .clip(CircleShape),
//            backgroundColor = shimmerBackgroundColor,
//            highlightColor = shimmerHighlightColor,
//            animationDuration = animationDuration
//        )
//
//        // Text placeholders
//        Column(modifier = Modifier.weight(1f)) {
//            ShimmerBox(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(20.dp)
//                    .clip(RoundedCornerShape(10.dp)),
//                backgroundColor = shimmerBackgroundColor,
//                highlightColor = shimmerHighlightColor,
//                animationDuration = animationDuration
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            ShimmerBox(
//                modifier = Modifier
//                    .fillMaxWidth(fraction = 0.5f)
//                    .height(20.dp)
//                    .clip(RoundedCornerShape(10.dp)),
//                backgroundColor = shimmerBackgroundColor,
//                highlightColor = shimmerHighlightColor,
//                animationDuration = animationDuration
//            )
//        }
//    }
//}
//
//@Composable
//private fun LoadingList(
//    shimmerBackgroundColor: Color,
//    shimmerHighlightColor: Color,
//    animationDuration: Int
//) {
//    repeat(times = 15) {
//        ShimmerBox(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(30.dp)
//                .padding(10.dp)
//                .clip(RoundedCornerShape(10.dp)),
//            backgroundColor = shimmerBackgroundColor,
//            highlightColor = shimmerHighlightColor,
//            animationDuration = animationDuration
//        )
//    }
//}
//
//@Composable
//private fun ShimmerBox(
//    modifier: Modifier = Modifier,
//    backgroundColor: Color,
//    highlightColor: Color,
//    animationDuration: Int
//) {
//    val transition = rememberInfiniteTransition(label = "")
//    val translateAnim = transition.animateFloat(
//        initialValue = 0f,
//        targetValue = 1000f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(
//                durationMillis = animationDuration,
//                easing = LinearEasing
//            ),
//            repeatMode = RepeatMode.Restart
//        ), label = ""
//    )
//
//    val brush = Brush.linearGradient(
//        colors = listOf(
//            backgroundColor,
//            highlightColor,
//            backgroundColor
//        ),
//        start = Offset(translateAnim.value - 500, translateAnim.value - 500),
//        end = Offset(translateAnim.value, translateAnim.value)
//    )
//
//    Box(
//        modifier = modifier
//            .background(brush = brush)
//    )
//}*/

/*
@Composable
private fun LoadingContent(
    modifier: Modifier = Modifier,
    shimmerBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ShimmerEffect(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(shimmerBackgroundColor)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(shimmerBackgroundColor)
                )
                Spacer(modifier = Modifier.height(10.dp))
                ShimmerEffect(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = 0.5f)
                        .height(20.dp)
                        .background(shimmerBackgroundColor)
                )
            }
        }
        repeat(times = 15) {
            ShimmerEffect(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(shimmerBackgroundColor)
            )
        }
    }
}

 */

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    shimmerBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    shimmerHighlightColor: Color = MaterialTheme.colorScheme.surface,
    animationDuration: Int = 1000
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with avatar and text placeholders
        LoadingHeader(
            shimmerBackgroundColor = shimmerBackgroundColor,
            shimmerHighlightColor = shimmerHighlightColor,
            animationDuration = animationDuration
        )

        Spacer(modifier = Modifier.height(16.dp))

        // List of card placeholders
        LoadingList(
            shimmerBackgroundColor = shimmerBackgroundColor,
            shimmerHighlightColor = shimmerHighlightColor,
            animationDuration = animationDuration
        )
    }
}

@Composable
private fun LoadingHeader(
    shimmerBackgroundColor: Color,
    shimmerHighlightColor: Color,
    animationDuration: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Avatar placeholder
            ShimmerBox(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                backgroundColor = shimmerBackgroundColor,
                highlightColor = shimmerHighlightColor,
                animationDuration = animationDuration
            )

            // Text placeholders
            Column(modifier = Modifier.weight(1f)) {
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    backgroundColor = shimmerBackgroundColor,
                    highlightColor = shimmerHighlightColor,
                    animationDuration = animationDuration
                )
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.7f)
                        .height(20.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    backgroundColor = shimmerBackgroundColor,
                    highlightColor = shimmerHighlightColor,
                    animationDuration = animationDuration
                )
            }
        }
    }
}

@Composable
private fun LoadingList(
    shimmerBackgroundColor: Color,
    shimmerHighlightColor: Color,
    animationDuration: Int
) {
    LazyColumn {
        items(5) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        backgroundColor = shimmerBackgroundColor,
                        highlightColor = shimmerHighlightColor,
                        animationDuration = animationDuration
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        backgroundColor = shimmerBackgroundColor,
                        highlightColor = shimmerHighlightColor,
                        animationDuration = animationDuration
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.5f)
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        backgroundColor = shimmerBackgroundColor,
                        highlightColor = shimmerHighlightColor,
                        animationDuration = animationDuration
                    )
                }
            }
        }
    }
}

@Composable
private fun ShimmerBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    highlightColor: Color,
    animationDuration: Int
) {
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            backgroundColor,
            highlightColor,
            backgroundColor
        ),
        start = Offset(translateAnim.value - 500, translateAnim.value - 500),
        end = Offset(translateAnim.value, translateAnim.value)
    )

    Box(
        modifier = modifier
            .background(brush = brush)
    )
}


@Preview(showBackground = true)
@Composable
private fun PreviewLoadingContent() {
    LoadingContent()
}