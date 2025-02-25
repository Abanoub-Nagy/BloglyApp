package com.example.bloglyapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.bloglyapp.presentation.blog_content.BlogContentScreen
import com.example.bloglyapp.presentation.blog_list.BlogListScreen
import com.example.bloglyapp.presentation.blog_list.BlogListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.BlogListScreen,
    ) {
        composable<Route.BlogListScreen> {
            val viewModel = koinViewModel<BlogListViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            BlogListScreen(
                state = state,
                event = viewModel.event,
                onBlogCardClick = {id ->
                    navController.navigate(Route.BlogContentScreen(id))
                }
            )
        }
        composable<Route.BlogContentScreen> {
            BlogContentScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }

}