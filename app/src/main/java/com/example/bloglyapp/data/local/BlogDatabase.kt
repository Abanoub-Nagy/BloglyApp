package com.example.bloglyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bloglyapp.data.local.entity.BlogContentEntity
import com.example.bloglyapp.data.local.entity.BlogEntity

@Database(
    entities = [BlogEntity::class, BlogContentEntity::class],
    version = 2,
    exportSchema = false
)
abstract class BlogDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao
}