package com.mayursarode.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class ApiSource(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = ""
)