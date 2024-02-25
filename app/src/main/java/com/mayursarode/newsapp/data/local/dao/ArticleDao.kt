package com.mayursarode.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.mayursarode.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): Flow<List<Article>>

    @Insert
    fun insertAll(articles: List<Article>)

    @Query("DELETE FROM article")
    fun deleteAll()

    @Transaction
    fun deleteAllInsertAll(articles: List<Article>) {
        deleteAll()
        insertAll(articles)
    }
}