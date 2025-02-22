package com.example.bloglyapp.di

import com.example.bloglyapp.data.remote.HttpClientFactory
import com.example.bloglyapp.data.remote.KtorRemoteBlogDataSource
import com.example.bloglyapp.data.remote.RemoteBlogDataSource
import com.example.bloglyapp.data.repository.BlogRepoImpl
import com.example.bloglyapp.domain.repository.BlogRepo
import com.example.bloglyapp.presentation.blog_list.BlogListViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinMainModule = module {
    single {
        HttpClientFactory.create(OkHttp.create())
    }
    singleOf(::KtorRemoteBlogDataSource).bind<RemoteBlogDataSource>()
    singleOf(::BlogRepoImpl).bind<BlogRepo>()

    viewModelOf(::BlogListViewModel)
}