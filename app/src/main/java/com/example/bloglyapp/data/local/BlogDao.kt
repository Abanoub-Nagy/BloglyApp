package com.example.bloglyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bloglyapp.data.local.entity.BlogContentEntity
import com.example.bloglyapp.data.local.entity.BlogEntity

@Dao
interface BlogDao {

    @Query("SELECT * FROM blogs")
    suspend fun getAllBlogs(): List<BlogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogs(blogs: List<BlogEntity>)

    @Query("SELECT * FROM blogs WHERE id = :id")
    suspend fun getBlogById(id: Int): BlogEntity?

    @Query("DELETE FROM blogs")
    suspend fun deleteAllBlogs()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogContent(content: BlogContentEntity)

    @Query("SELECT * FROM blog_content WHERE blogId = :id")
    suspend fun getBlogContentById(id: Int): BlogContentEntity?

}