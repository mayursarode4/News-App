package com.mayursarode.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.mayursarode.newsapp.data.local.entity.Source

data class ApiSource(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = ""
)

fun ApiSource.toEntitySource(): Source {
    return Source(
        id = id,
        name = name
    )
}