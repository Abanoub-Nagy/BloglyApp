package com.example.bloglyapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bloglyapp.data.util.Constant

@Entity(tableName = Constant.BLOG_TABLE_NAME)
data class BlogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val contentUrl: String,
    val thumbnailUrl: String
)
