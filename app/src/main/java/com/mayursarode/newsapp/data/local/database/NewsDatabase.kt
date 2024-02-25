package com.mayursarode.newsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mayursarode.newsapp.data.local.dao.ArticleDao
import com.mayursarode.newsapp.data.local.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

}