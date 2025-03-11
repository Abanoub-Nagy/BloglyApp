package com.example.bloglyapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bloglyapp.data.util.Constant.BLOG_CONTENT_TABLE_NAME

@Entity(tableName = BLOG_CONTENT_TABLE_NAME)
data class BlogContentEntity(
    @PrimaryKey
    val blogId: Int,
    val content: String
)
