package com.example.bloglyapp.data.local

import android.content.Context
import androidx.room.Room
import com.example.bloglyapp.data.util.Constant

object DatabaseFactory {
    fun create(context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            klass = BlogDatabase::class.java,
            name = Constant.BLOG_DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}