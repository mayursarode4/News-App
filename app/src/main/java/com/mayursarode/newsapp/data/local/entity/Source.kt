package com.mayursarode.newsapp.data.local.entity

import androidx.room.ColumnInfo
import com.mayursarode.newsapp.data.model.ApiSource

data class Source(
    @ColumnInfo(name = "sourceId")
    val id: String?,
    @ColumnInfo(name = "sourceName")
    val name: String = ""
)

fun Source.toSourceEntity(): ApiSource {
    return ApiSource(
        id = id,
        name = name
    )
}
